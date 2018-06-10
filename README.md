# test3

>
Generación JAR

>
Simplemente utilizando maven se puede realizar el package:
>

>
mvn clean package
>

> Hay varias soluciones posibles:
>

>	- Mediante reflection, lo primero nos apoyamos en una clase para crear los driver dependiendo de su class
>

```java

public class CreateConcreteDriver<T extends WebDriver> {

	// declare the class instance
	private Class<T> tClass;

	public CreateConcreteDriver(Class<T> tClass) {
		this.tClass = tClass;
	}

	/**
	 * Create new concrete WebDriver element.
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public T newElement() throws InstantiationException, IllegalAccessException {
		return tClass.newInstance();
	}

}

```

	Entonces a la hora de crear las enumeraciones lo hacemos así:
	
```java

	CHROME( "chrome", new CreateConcreteDriver<ChromeDriver>(ChromeDriver.class), ChromeDriverManager.getInstance().version( "2.24" ) ),
    FIREFOX( "firefox", new CreateConcreteDriver<FirefoxDriver>(FirefoxDriver.class), FirefoxDriverManager.getInstance() ),
    EDGE( "edge", new CreateConcreteDriver<EdgeDriver>(EdgeDriver.class), EdgeDriverManager.getInstance() ),
    IE( "ie", new CreateConcreteDriver<InternetExplorerDriver>(InternetExplorerDriver.class), InternetExplorerDriverManager.getInstance() ),
    MARIONETTE( "marionette", new CreateConcreteDriver<FirefoxDriver>(FirefoxDriver.class), FirefoxDriverManager.getInstance() ),
    OPERA( "opera", new CreateConcreteDriver<OperaDriver>(OperaDriver.class), OperaDriverManager.getInstance() ),
    PHANTOMJS( "phantomjs", new CreateConcreteDriver<PhantomJSDriver>(PhantomJSDriver.class), PhantomJsDriverManager.getInstance() ),
    NONE( "test", new CreateConcreteDriver<NoneDriver>(NoneDriver.class), VoidDriverManager.getInstance().version( "1" ) );

	// Browser Name
	private final String browserName;
	
	// Browser Manager
    private final BrowserManager browserManager;
    
    // Concrete Driver by reflection
    private final CreateConcreteDriver<?> createConcreteDriver;
    
    /**
     * Construct Browser Enum with:
     * 
     * 	- Name
     *  - CreateConcreteDriver
     *  - BroserManager impl
     *  
     * @param browserName
     * @param CreateConcreteDriver
     * @param browserManager
     */
    private BrowserManagerEnum( final String browserName, final CreateConcreteDriver<?> createConcreteDriver, final BrowserManager browserManager ) {
        this.browserName = browserName;
        this.browserManager = browserManager;
        this.createConcreteDriver = createConcreteDriver;
    }
	
	/**
     * Get Driver.
     * 
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public WebDriver getDriver() throws InstantiationException, IllegalAccessException {
        return this.createConcreteDriver.newElement();
    }

```

>	Con esta solución se añaden nuevas exceptions que se deben controlar.
>


>	-	Utilizando Java 8:
>

	Quitamos los switch de ambos métodos getBrowserManager y getDriver, y añadimos al constructor de la enum BrowserManager y WebDriver y quedaría así:

```java
	private final String browserName;
	private final BrowserManager browserManager;
	private final WebDriver webDriver;
    
	private BrowserManagerEnum( final String browserName, final WebDriver webDriver, final BrowserManager browserManager ) {
		this.browserName = browserName;
		this.browserManager = browserManager;
		this.webDriver = webDriver;
	}
```

	Y luego por ejemplo para CHROME seria:
	
```java
	CHROME( "chrome", new ChromeDriver(), ChromeDriverManager.getInstance().version( "2.24" ))
```
	
	Pero así nos daría fallo al no estar preparada la infraestructura para dichos driver, entonces entra en juego Java 8 y si no me equivoco lo más adecuado para aquí seria una FuntionalInterface y 	concretamente la Supplier, ya que es la utilizada para creacción de objetos, en este caso seria algo así:
	
```java
	CHROME( "chrome",() -> new ChromeDriver(), ChromeDriverManager.getInstance().version( "2.24" ) ),
	....
	
	private final String browserName;
	private final BrowserManager browserManager;
	private final Supplier<WebDriver> webDriverSupplier;
    
	private BrowserManagerEnum( final String browserName, final Supplier<WebDriver> webDriverSupplier, final BrowserManager browserManager ) {
		this.browserName = browserName;
		this.browserManager = browserManager;
		this.webDriverSupplier = webDriverSupplier;
	}

	public BrowserManager getBrowserManager() {
		return browserManager;
	}

	public WebDriver getDriver() {
		return webDriverSupplier.get();
	}
```
