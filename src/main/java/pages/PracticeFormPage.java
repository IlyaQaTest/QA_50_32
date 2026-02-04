package pages;

import dto.Student;
import enums.Gender;
import enums.Hobbies;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class PracticeFormPage extends BasePage {
    public PracticeFormPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement inputName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(id = "userEmail")
    WebElement inputUserEmail;
    @FindBy(id = "userNumber")
    WebElement inputMobile;
    @FindBy(id = "dateOfBirthInput")
    WebElement fieldDateOfBirt;
    @FindBy(id = "subjectsInput")
    WebElement inputSubject;
    @FindBy(id = "currentAddress")
    WebElement inputAddress;
    @FindBy(id = "react-select-3-input")
    WebElement inputState;
    @FindBy(id = "react-select-4-input")
    WebElement inputCity;
    @FindBy(id = "submit")
    WebElement btnSubmit;
    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement modalMessage;

    public void typePracticeForm(Student student) {
        hideBanner();
        hideFooter();
        scroll();
        inputName.sendKeys(student.getName());
        inputLastName.sendKeys(student.getLastName());
        inputUserEmail.sendKeys(student.getEmail());
        inputMobile.sendKeys(student.getMobile());
        inputAddress.sendKeys(student.getAddress());
        typeGender(student.getGender());
        typeDateOfBirth(student.getDateOfBirth());
        typeSubjects(student.getSubject());
        typeHobbies(student.getHobbies());
        typeStateCity(student.getState(), student.getCity());
        btnSubmit.click();
    }

    private void typeStateCity(String state, String city) {
        inputState.sendKeys(state);
        inputState.sendKeys(Keys.ENTER);

        inputCity.sendKeys(city);
        inputCity.sendKeys(Keys.ENTER);
    }

    public boolean validateModalMessage(String text) {
        return validateTextInElement(modalMessage, text);
    }

    private void typeGender(Gender gender) {
        WebElement btnGender = driver.findElement(By.xpath(gender.getLocator()));
        btnGender.click();
    }

    private void typeDateOfBirth(String dateOfBirth) {
        fieldDateOfBirt.click();
        String operationSystem = System.getProperty("os.name");// find system
        System.out.println("OS.NAME = " + operationSystem);
        if (operationSystem.startsWith("win"))
            fieldDateOfBirt.sendKeys(Keys.chord(Keys.CONTROL, "a"));// select all win
        else if (operationSystem.startsWith("Mac"))
            fieldDateOfBirt.sendKeys(Keys.chord(Keys.COMMAND, "a"));// select all mac
        // 2. Очищаем поле через JavaScript напрямую
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].value = '';", fieldDateOfBirt);
        fieldDateOfBirt.sendKeys(dateOfBirth);
        fieldDateOfBirt.sendKeys(Keys.ENTER);
    }

    private void typeSubjects(String subjects) {
        inputSubject.click();
        String[] arr = subjects.split(",");// massive
        for (String s : arr) {
            inputSubject.sendKeys(s);
            inputSubject.sendKeys(Keys.ENTER);
        }

    }

    private void typeHobbies(List<Hobbies> hobbies) {
        for (Hobbies h : hobbies) {
            switch (h) {
                case MUSIC -> driver.findElement(By.xpath(h.getLocator())).click();
                case SPORTS -> driver.findElement(By.xpath(h.getLocator())).click();
                case READING -> driver.findElement(By.xpath(h.getLocator())).click();
            }
        }
    }


}
