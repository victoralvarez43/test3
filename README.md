# test3

>
Generacion JAR

>
Simplemente utilizando maven se puede realizar el package:

>
mvn clean package

>
Utilizando Java 8:


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
