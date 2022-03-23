package qa.automation;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;


public class ProductTests extends TestUtil {

    @Test
    public void selectDifferentOrder() throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user");

        //find element using xpath and indexing the results
        WebElement passwordInput = driver.findElement(By.xpath("(//input[@class='input_error form_input'])[2]"));
        passwordInput.click();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        //Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropDownSortingOptions = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        //WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        //dropDownSortingOptions.wait(0x2);
        wait.until(ExpectedConditions.elementToBeClickable(dropDownSortingOptions));
        dropDownSortingOptions.click();

        //fluent wait
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2)) // how often it will be checked for the presence of the element
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement lowToHighPriceOption = driver.findElement(By.cssSelector("[value=lohi]"));

        fluentWait.until(ExpectedConditions.elementToBeClickable(lowToHighPriceOption));
        lowToHighPriceOption.click();
        //Thread.sleep(3000); - not good to be used

    }

    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
    }
}
