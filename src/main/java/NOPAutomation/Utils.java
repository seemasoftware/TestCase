package NOPAutomation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils extends BasePage {

    public String generateEmail(String startValue) {
        String email = startValue.concat(new Date().toString());
        return email;
    }

    public static String rendomDate() {
        DateFormat format = new SimpleDateFormat("ddMMyyHHMMss");
        return format.format(new Date());
    }


    //1.Method to LunchBrowser
    public static void LunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");
        //Open The Browser
        driver = new ChromeDriver();
        //Maximise the browser Window
        driver.manage().window().fullscreen();
        //set implicitly for driver object.
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        //Open the website
        driver.get("https://demo.nopcommerce.com");
    }

    //2.Close Browser Method
    public static void BrowserClose() {
        //Drive will close browser.
        driver.quit();
    }

    //3.Screenshort Method
    //public static void getScreenshot() throws IOException {
    // File scrFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // FileUtils.copyFile(scrFile,new File("src\\main\\Resources\\Screenshort\\screenshot.png"+rendomDate()+".png"),true);
    //}

    //3.
    public static void takeScreenshot(WebDriver driver) {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot srcShot = ((TakesScreenshot) driver);

        //Call getScreenshotAs method to create image file
        File ScrFile = srcShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile = new File("src\\main\\Resources\\Screenshort\\screenshot.png");

        //Copy file at destination
        try {
            FileUtils.copyFile(ScrFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4.Close Browser Method
    public static void CloseBrowser() {
        //Drive will close browser.
        driver.quit();
    }

    //5.Select by Value Drag and drop Method
    public void SelectByValue(By by, String value) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }

    //6. Select by Index Number Drag and drop Method
    public void SelectByIndex(By by, int value) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(value);
    }

    //7.Select by Visibility Text Method
    public void SelectByVisibilityText(By by, String value) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(value);
    }

    //8.Clickable Explicitly Wait Method
    protected static void waitForClickable(By by, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    //9.Explicitly wait until ElementVisible
    public static void waitForElementVisible(By by, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //10.Check for Explicitly AlertPresent
    public static void waitForAlertPresent(long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //11. Checking for Explicitly Wait for Invisible Element
    public void waitForInvisiblePresent(By locator, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //12.Checking for Explicitly Wait for Invisible Text
    public void InvisibilityByText(By by, long value, String text) {
        WebDriverWait wait = new WebDriverWait(driver, value);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }

    //13.Checking for Explicitly Wait for scrollToElement
    public void ScrollToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement((WebElement) by).perform();
    }

    //14.Explicitly Wait for scrollToElement and Click
    public void ScrollToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement((WebElement) by).click();
    }

    //15.Enter Text Method
    public void EnterText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    //16.Get Text method
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    //17.Click on element method
    public void ClickElement(By by) {
        driver.findElement(by).click();
    }

    //18.Clear Text form inout box/area
    public void ClearText(By by) {
        driver.findElement(by).clear();
    }

    //19.Clear and enter text in input field
    public void ClearAndText(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    //20.Wait for fixed time given in seconds
    public void WaitForFixedTime(long time) throws InterruptedException {
        driver.wait(time);
    }

    //21.Method for get Attribute
    public String getAttribute(By by, String text) {
        return driver.findElement(by).getAttribute(text);
    }

    //22.Method for Navigate
    public void Navigate(String text) {
        driver.navigate().to(text);
    }

    //23.Explicity wait
    public void ExplicityWait(long value, TimeUnit time) {
        driver.manage().timeouts().implicitlyWait(value, time);
    }

    //24.Implicity wait until pageload.
    public void ImplicityWaitUntilPageLoad(long value, TimeUnit time) {
        driver.manage().timeouts().pageLoadTimeout(value, time);
    }

    //28.Dynamic Email Address
    public static String DynamicAddress() {
        String text = LoadProps.getProperty("EmailPart1") + rendomDate() + LoadProps.getProperty("EmailPart2");
        return text;
    }


}