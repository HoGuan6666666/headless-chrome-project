package article;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class SearchArticle {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    public void searchArticle() {
        //chose driver type
        String os = (System.getProperty("os.name"));

        if (os.equalsIgnoreCase("Mac OS X"))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        else System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);

        baseUrl = "http://retractiondatabase.org/RetractionSearch.aspx";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        System.out.println("setup done.");

        //navigate to homepage of website
        driver.get(baseUrl);

        //find search box and enter can into the box
        driver.findElement(By.id("txtSrchTitle")).clear();
        driver.findElement(By.id("txtSrchTitle")).sendKeys("can");

        //press enter key to start search
        Actions action = new Actions(driver);
        action .sendKeys(Keys.ENTER).perform();

        //find table header and print it
        String title = driver.findElement(By.xpath("//th[@class=\"header headerSortUp smallFont\"]")).getText();
        System.out.println(title);

        System.out.println("testing done.");
        System.out.println("for hook testing");

        if (driver != null)
            driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }

        System.out.println("Tear down done.");
    }

    public static void main(String [] args) {
        SearchArticle test = new SearchArticle();
        test.searchArticle();
    }

}
