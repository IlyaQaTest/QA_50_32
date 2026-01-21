package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class XpathSelectors {
    WebDriver driver = new ChromeDriver();

    @Test
    public void selectorsTextBox() {
        driver.get("https://demoqa.com/elements");
        driver.manage().window().maximize();
        pause(2);
        hideBanner();
        hideFooter();
        WebElement btnTextBox = driver.findElement(By.xpath("//span[text()='Text Box']"));
        btnTextBox.click();
        WebElement fieldFullName = driver.findElement(By.xpath("//input[@placeholder='Full Name']"));
        fieldFullName.sendKeys("Ilya");
        pause(2);
        WebElement fieldEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        fieldEmail.sendKeys("ilya123@gmail.com");
        pause(2);
        WebElement fieldCarAddress = driver.findElement(By.xpath("//*[@placeholder='Current Address']"));
        fieldCarAddress.sendKeys("street 1");
        scroll();
        WebElement btnSub = driver.findElement(By.xpath("//button[@id='submit']"));
        btnSub.click();
        pause(2);
        WebElement output = driver.findElement(By.id("output"));
        System.out.println(output.getText());
        pause(3);
        driver.quit();
    }

    public void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0 ,500);");

    }

    public void pause(int time) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void hideBanner() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#fixedban').style.display ='none'");
    }

    public void hideFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('footer').style.display='none'");
    }
}
