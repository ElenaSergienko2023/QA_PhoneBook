package com.phonebook.tests;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    //precondition: user should be logged out
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test (priority = 1)
    public void loginPositiveTest() {
        //click on Login link
        //driver.findElement(By.cssSelector("[href='/login']")).click();
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegistrationForm(new User().setEmail("usual@gmail.com").setPassword("Usual1234$"));
        //click on Registration button
        app.getUser().clickOnLoginButton();
        //assert Sign out button is displayed
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test (priority = 2)
    public void loginNegativeWithoutPasswordTest() {
        //click on Login link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegistrationForm(new User().setEmail("usual@gmail.com"));
        //click on Registration button
        app.getUser().clickOnLoginButton();
        //assert Sign out button is displayed
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}
