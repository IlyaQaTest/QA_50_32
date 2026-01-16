package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CssSelectors {
    WebDriver driver = new ChromeDriver();

    @Test
    public void selectorHomePage(){
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        pause(3);
        driver.navigate().to("https://demoqa.com/elements");
        pause(3);
        driver.navigate().back();
        pause(3);
        driver.navigate().refresh();
        pause(3);
        //driver.close();
        driver.quit();
    }

    @Test
    public void selectorsCssTests(){
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        WebElement footer = driver.findElement(By.tagName("footer"));
        System.out.println(footer.getTagName());
        // Удаляем футер и рекламу, чтобы они не мешали
        ((JavascriptExecutor) driver).executeScript("document.getElementById('adplus-anchor').style.display='none';");
        ((JavascriptExecutor) driver).executeScript("document.querySelector('footer').style.display='none';");
        // Используем явное ожидание (Explicit Wait)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ищем контейнер карточки, так как по нему кликать надежнее
        WebElement firstCard = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card")));
        firstCard.click();
        WebElement radioButton = driver.findElement(By.cssSelector("#item-2"));
        radioButton.click();
        WebElement radioButtonYes = driver.findElement(By.cssSelector("label[for='yesRadio']"));
        radioButtonYes.click();
        pause(3);
        driver.navigate().back();
        driver.navigate().back();
        WebElement divWidgets = driver.findElement(By.
                cssSelector("div[class='category-cards'] " +
                        "[class='card mt-4 top-card']:nth-child(4)"));
        divWidgets.click();
    }

    public void pause(int time) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
