# test3


## Generación JAR

>
Simplemente utilizando maven se puede realizar el package:

>
mvn clean package

> ------------------

>He incluido este git en travis para poder tener entorno de CI.

>[https://travis-ci.org/victoralvarez43/test3](https://travis-ci.org/victoralvarez43/test3)

## Posibles Soluciones:

* **La solución que he implementado en el proyecto, se trata de crear una factoría para crear los Driver y obtener los Manager, que deben implementar todas las factorías de cada navegador.**
	
```java

/**
 * Interface that must be implemented by all the specific factories of browsers.
 *  
 * @author victor
 *
 * @param <T>
 */
public interface WebDriverFactory<T extends WebDriver> {

	/**
	 * Create new element.
	 * 
	 * @return
	 */
	public T newElement();
	
	/**
	 * Get Browser Manager.
	 * 
	 * @return
	 */
	public BrowserManager getBrowserManager();
}

```

Y por ejemplo la implementación para Firefox:
	
```java

/**
 * Factory to create Firefox browser driver.
 * 
 * @author victor
 *
 */
public class FirefoxFactory implements WebDriverFactory<FirefoxDriver>{

	@Override
	public FirefoxDriver newElement() {
		return new FirefoxDriver();
	}
	
	@Override
	public BrowserManager getBrowserManager() {
		return FirefoxDriverManager.getInstance();
	}
}


```

Luego en la Enum, añadimos al constructor las factorias concretas, y por ultimo en el metodo getDriver se obtiene el driver concreto mediante la ejecución del metodo newElement() y en el metodo getBrowserManager se obtiene el manager mediante la ejecución del metodo getBrowserManager():
	
```java

	CHROME( "chrome", new ChromeFactory() ),
	...
	
	// Browser Name
	private final String browserName;
	
    // WebDriverFactory
    private final WebDriverFactory<?> webDriverFactory;
    
    /**
     * Construct Browser Enum with:
     * 
     * 	- Name
     *  - WebDriverFactory
     *  - BroserManager impl
     *  
     * @param browserName
     * @param webDriverFactory
     */
    private BrowserManagerEnum( final String browserName, final WebDriverFactory<?> webDriverFactory ) 		{
        this.browserName = browserName;
        this.webDriverFactory = webDriverFactory;
    }

    /**
     * Get Driver.
     * 
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public WebDriver getDriver() throws InstantiationException, IllegalAccessException {
        return this.webDriverFactory.newElement();
    }
    
    /**
     * Get Browser Manager.
     * 
     * @return
     */
    public BrowserManager getBrowserManager() {
    	return this.webDriverFactory.getBrowserManager();
    }

	
```

He optado por esta solución porque aunque a priori parezca que se crean demasiadas clases para simplemente un constructor y un get en cada una de ellas, lo veo muy útil para el futuro, primero definimos una interfaz que se puede ir aumentando con nuevas funcionalidades que surjan en un futuro, ademas de tener un buen patrón de factoría implementado y que cada clase sera la encargada de crear su correspondiente driver, y esto significa que por ejemplo podamos tener diferentes configuraciones parametrizadas ya bien con perfiles maven o resources o caches o bbdd o etc. para diferentes entornos, situaciones, etc.
	
A parte también se podría crear una nueva librería con esta parte para tenerlo separado, esta claro que para este ejemplo no es adecuado, pero si esta parte creciera demasiado podría ser un modulo nuevo e independiente.
	
En cambio en las otras dos opciones no podríamos modificar la creacción de un driver concreto sin afectar al resto.

> ------------------

* *Mediante reflection, lo primero nos apoyamos en una clase para crear los driver dependiendo de su class*


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

** Con esta solución se añaden nuevas exceptions que se deben controlar. **

> ------------------

* *Utilizando Java 8:*


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
	
Pero así nos daría fallo al no estar preparada la infraestructura para dichos driver, entonces entra en juego Java 8 y si no me equivoco lo más adecuado para aquí seria una FuntionalInterface y concretamente la Supplier, ya que es la utilizada para creacción de objetos, en este caso seria algo así:
	
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
