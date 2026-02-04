package tests;

import dto.Student;
import enums.Gender;
import enums.Hobbies;
import enums.StateCity;
import manager.AppManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FormsPage;
import pages.HomePage;
import pages.PracticeFormPage;

import java.util.ArrayList;
import java.util.List;

public class PracticeFormTest extends AppManager {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void studentRegFormPositiveTest() {
        List<Hobbies> hobbies = new ArrayList<>();
        hobbies.add(Hobbies.READING);
        hobbies.add(Hobbies.SPORTS);
        hobbies.add(Hobbies.MUSIC);
        new HomePage(getDriver()).clickBtnForms();
        new FormsPage(getDriver()).clickBtnPracticeForm();
        Student student = new Student("Vasya", "Vasilev", "vasya@gmail.com",
                Gender.MALE, "0123456789", "22 Dec 1999",
                "Maths,Physics,Chemistry",
                hobbies, "", "Address 1",
                StateCity.NCR.getState(), StateCity.NCR.getCity()[0]);
        new PracticeFormPage(getDriver()).typePracticeForm(student);
        Assert.assertTrue(new PracticeFormPage(getDriver())
                .validateModalMessage("Thanks for submitting the form"));
        System.out.println("Test passed");
    }

    @Test
    public void studentRegFormPositiveTestSoftAssert() {
        List<Hobbies> hobbies = new ArrayList<>();
        hobbies.add(Hobbies.READING);
        hobbies.add(Hobbies.SPORTS);
        hobbies.add(Hobbies.MUSIC);
        new HomePage(getDriver()).clickBtnForms();
        new FormsPage(getDriver()).clickBtnPracticeForm();
        Student student = new Student("Vasya", "Vasilev", "vasya@gmail.com",
                Gender.MALE, "0123456789", "22 Dec 1999",
                "Maths,Physics,Chemistry",
                hobbies, "", "Address 1",
                StateCity.NCR.getState(), StateCity.NCR.getCity()[0]);
        new PracticeFormPage(getDriver()).typePracticeForm(student);
        softAssert.assertTrue(new PracticeFormPage(getDriver())
                .validateModalMessage("Wrong text"), "validate wrong massage 51");
        softAssert.assertTrue(new PracticeFormPage(getDriver())
                .validateModalMessage("Thanks for submitting the form"));
        softAssert.assertAll();
    }

    @Test
    public void studentRegFormPositiveTestSoftAssert_CheckFields() {
        List<Hobbies> hobbies = new ArrayList<>();
        hobbies.add(Hobbies.READING);
        hobbies.add(Hobbies.SPORTS);
        hobbies.add(Hobbies.MUSIC);
        new HomePage(getDriver()).clickBtnForms();
        new FormsPage(getDriver()).clickBtnPracticeForm();
        Student student = new Student("Vasya", "Vasilev", "vasya@gmail.com",
                Gender.MALE, "0123456789", "22 Dec 1999",
                "Maths,Physics,Chemistry",
                hobbies, "", "Address 1",
                StateCity.NCR.getState(), StateCity.NCR.getCity()[0]);
        new PracticeFormPage(getDriver()).typePracticeForm(student);
        Assert.assertTrue(new PracticeFormPage(getDriver())
                .validateModalMessage("Thanks for submitting the form"));
        softAssert.assertTrue(getDriver().findElement(By.xpath("//tbody/tr/td[2]"))
                .getText().contains(student.getLastName()),"validate LastName");
        softAssert.assertTrue(getDriver().findElement(By.xpath("//tbody/tr/td[2]"))
                .getText().contains(student.getName()),"validate Name");
        softAssert.assertTrue(getDriver().findElement(By.xpath("//tbody/tr[4]/td[2]"))
                .getText().contains(student.getMobile()),"validate Mobile");
        softAssert.assertAll();
    }
}
