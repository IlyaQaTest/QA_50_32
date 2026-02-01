package tests;

import dto.Student;
import enums.Gender;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FormsPage;
import pages.HomePage;
import pages.PracticeFormPage;

public class PracticeFormTest extends AppManager {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void studentRegFormPositiveTest() {
        new HomePage(getDriver()).clickBtnForms();
        new FormsPage(getDriver()).clickBtnPracticeForm();
        Student student = new Student("Vasya", "Vasilev", "vasya@gmail.com",
                Gender.MALE, "0123456789", "22 Dec 1999",
                "Maths,Physics,Chemistry",
                "", "", "Address 1", "", "");
        new PracticeFormPage(getDriver()).typePracticeForm(student);
        Assert.assertTrue(new PracticeFormPage(getDriver())
                .validateModalMessage("Thanks for submitting the form"));
    }
}
