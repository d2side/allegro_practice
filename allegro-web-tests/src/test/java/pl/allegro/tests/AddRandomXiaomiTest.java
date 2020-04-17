package pl.allegro.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddRandomXiaomiTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("https://allegro.pl/");
    driver.findElement(By.cssSelector("button[data-role='accept-consent']")).click();
  }

  @Test
  public void testAddRandomXiaomi() throws Exception {
    clickOnGlobalSearch();
    typeProductTitle();
    confirmSearch();
    selectFirstResultProduct();
    goToCartAfterProductAdding();
  }

  private void selectFirstResultProduct() {
    driver.findElement(By.xpath("//img[contains(@src,'https://a.allegroimg.com/s128/030058/9eb5ceba42a783c805ef2e7b53d3')]")).click();
  }

  private void goToCartAfterProductAdding() {
    driver.findElement(By.linkText("przejdź do koszyka")).click();
  }

  private void confirmSearch() {
    driver.findElement(By.name("string")).sendKeys(Keys.ENTER);
  }

  private void typeProductTitle() {
    driver.findElement(By.name("string")).sendKeys("xiaomi");
  }

  private void clickOnGlobalSearch() {
    driver.findElement(By.name("string")).click();
    driver.findElement(By.name("string")).clear();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
