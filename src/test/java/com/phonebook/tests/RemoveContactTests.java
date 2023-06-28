package com.phonebook.tests;

import com.phonebook.model.Contact;
import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegistrationForm(new User().setEmail("usual@gmail.com").setPassword("Usual1234$"));
        app.getUser().clickOnLoginButton();

        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(
                new Contact().setName("Test")
                        .setLastname("Testoff")
                        .setPhone("112233445566")
                        .setEmail("Test@test.com")
                        .setAddress("London")
                        .setDesc("student"));
        app.getContact().clickOnSaveButton();
    }


    @Test
    public void removeContactTest() {
        int sizeBefore = app.getContact().sizeOfContacts();

        app.getContact().openContactForm("Test");
        app.getContact().clickOnRemoveButton();
        app.getContact().pause(2000);

        int sizeAfter = app.getContact().sizeOfContacts();

        Assert.assertEquals(sizeAfter, sizeBefore-1);
    }
}
