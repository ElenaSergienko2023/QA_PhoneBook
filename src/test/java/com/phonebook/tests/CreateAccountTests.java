package com.phonebook.tests;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{

    //precondition: user should be logged out
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    //public void newUserRegistrationPositiveTest() {

    public void existedUserRegistrationNegativeTest() {
        //click on Login link
        //driver.findElement(By.cssSelector("[href='/login']")).click();
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegistrationForm(new User().setEmail("usual@gmail.com").setPassword("Usual1234$"));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();

//      assert Sign out button is displayed
//      Assert.assertTrue(isElementPresent1(By.xpath("//button[.='Sign Out']")));

        //assert warning appears
        Assert.assertTrue(app.getUser().isAlertPresent());
    }


}
