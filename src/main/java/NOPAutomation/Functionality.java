package NOPAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class Functionality extends Utils {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void Setup() {
        //Calling method to Lunchbrowser
        LunchBrowser();
    }

    @AfterMethod
     public void TearDown()
    {
    //Calling method to Closebrowser
        CloseBrowser();
     }

    @Test (priority = 0)
    public void UserShouldBeAddTwoProductInCompareList() {
//Click on first product - Build your own computer
        ClickElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/1\"),!1']"));
//Wait for pop up window to be display
        waitForElementVisible(By.xpath("//p[@class=\"content\"]"), 5);
//Store expected string
        String expectedmsg1 = "The product has been added to your product comparison";
//Store actualmsg in string
        String actualmsg1 = getTextFromElement(By.xpath("//p[@class=\"content\"]"));
//Checking for Compare list with soft method
        softAssert.assertEquals(expectedmsg1, actualmsg1);
//Click to close pop up Menu
        ClickElement(By.xpath("//span[@class=\"close\"]"));
//Click on second product Apple MacBook Pro 13-inch
        ClickElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/4\"),!1']"));
//Wait for pop up window to be display
        waitForElementVisible(By.xpath("//p[@class=\"content\"]"), 5);
//Store expected string
        String expectedmsg2 = "The product has been added to your product comparison";
//Store actualmsg in string
        String actualmsg2 = getTextFromElement(By.xpath("//p[@class=\"content\"]"));
//Checking for Compare list with soft method
        softAssert.assertEquals(expectedmsg2, actualmsg2);
        waitForElementVisible(By.linkText("Compare products list"), 5);
//Click to close pop up Menu
        ClickElement(By.xpath("//span[@class=\"close\"]"));
        waitForElementVisible(By.linkText("Compare products list"), 5);
// Click on Compare list
        ClickElement(By.linkText("Compare products list"));
        String expectedProduct1 = "Build your own computer";
//Save actual product1 in string
        String actualProduct1 = getTextFromElement(By.linkText("Build your own computer"));
//checking for product in compare product 1 list with soft method.
        softAssert.assertEquals(expectedProduct1, actualProduct1);
 waitForElementVisible(By.linkText("Apple MacBook Pro 13-inch"),5);
//Save expected product2 in string
        String expectedProduct2 = "Apple MacBook Pro 13-inch";
//Save actual product1 in string
        String actualProduct2 = getTextFromElement(By.linkText("Apple MacBook Pro 13-inch"));
//checking for product in compare product 2 list with soft method.
        softAssert.assertEquals(expectedProduct2, actualProduct2);
//Clear compare list
        ClickElement(By.xpath("//a[@class=\"clear-list\"]"));
//Save expected message in string.
        String expectedmsg = "You have no items to compare.";
//Save actual message in string.
        String actualmsg = getTextFromElement(By.className("no-data"));
//Checking for message after clear compare list with soft method.
        softAssert.assertEquals(expectedmsg, actualmsg);

        softAssert.assertAll();
    }

    @Test (priority = 1)
    public void UserShouldbeAbleToAddCommentSuccessfullyAndAlwaysDisplayedAtTheBottom() {
//Click on first news details
        ClickElement(By.xpath("//div[2]/div/a[@class=\"read-more\"]"));
//Enter text in Title box
        EnterText(By.xpath("//input[@id=\"AddNewComment_CommentTitle\"]"), LoadProps.getProperty("title"));
//Store expected tile in string
        String expectedTitle = "Great News";
//Store actual title in string
        String actualTitle = getTextFromElement(By.xpath("//input[@id=\"AddNewComment_CommentTitle\"]"));
//checking expected and actual tile with soft method
        softAssert.assertEquals(actualTitle, expectedTitle);
//Enter text in Comment
        EnterText(By.xpath("//textarea[@id=\"AddNewComment_CommentText\"]"),LoadProps.getProperty("comment"));
// Store expected comment in string
        String expectedcomment = "Congratulations & Best Wishes on your Grand Opening. May your business be prosperous with many years to come.";
// Store actual comment in string
        String actualcomment = getTextFromElement(By.xpath("//textarea[@id=\"AddNewComment_CommentText\"]"));
//checking expected and actual comment with soft method.
        softAssert.assertEquals(actualcomment, expectedcomment);
//Click on New comment to submit comment.
        ClickElement(By.xpath("//input[@name=\"add-comment\"]"));
//Store expected successful msg in string
        String expectedSuccessfulMsg = "News comment is successfully added.";
// Store actual successful msg in string
        String actualSuccessfulMsg = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
//checking actual and expected msg with soft method.
        softAssert.assertEquals(expectedSuccessfulMsg, actualSuccessfulMsg);
//Execute JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
//This will scroll the web page till end.
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    @Test (priority = 2)
    public void UserShouldBeAbleSearchSpecificBrand() {

//Enter 'Nike' search word in search word
EnterText((By.xpath("//input[@class=\"search-box-text ui-autocomplete-input\"]")),LoadProps.getProperty("productsearch1"));
//Click on search button
ClickElement(By.xpath("//input[@type=\"submit\"]"));
//Get all web elements as a list
List<WebElement> itemlist = driver.findElements(By.xpath("//div[@class=\"product-grid\"]"));
// Declaring a string array to store all texts from web elements
List<String> textlist = new ArrayList<String >();
//check all elements with for each loop
  for (WebElement e:itemlist)
  {
   textlist.add(e.getText());
  }
//print all text list
  System.out.println(textlist.toString());
// checking text including 'Nike' word
  softAssert.assertTrue(textlist.toString().contains("Nike"));
//Enter 'Reebok' search word in search word
 EnterText((By.xpath("//input[@class=\"search-box-text ui-autocomplete-input\"]")),LoadProps.getProperty("productsearch2"));
//Click on search button
 ClickElement(By.xpath("//input[@type=\"submit\"]"));
//Store expected msg in string
 String expectedmsg = "No products were found that matched your criteria.";
// store actual msg in string
 String actualmsg = getTextFromElement(By.xpath("//div[@class=\"no-result\"]"));
 // checking actual with actual msg with soft assert method
 softAssert.assertEquals(expectedmsg,actualmsg);
 // checking for soft assert all
 softAssert.assertAll();
 }
 }

