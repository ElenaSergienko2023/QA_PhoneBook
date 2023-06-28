package com.phonebook.tests;

import com.phonebook.fw.DataProviders;
import com.phonebook.model.Contact;
import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegistrationForm(new User().setEmail("usual@gmail.com").setPassword("Usual1234$"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {
        //click on ADD Link
        app.getContact().clickOnAddLink();
//      int i = (int) (System.currentTimeMillis()/1000)%3600;
        //fill in all fields
        app.getContact().fillAddContactForm(
                new Contact().setName("Test")
                        .setLastname("Testoff")
                        .setPhone("112233445566")
                        .setEmail("Test@test.com")
                        .setAddress("London")
                        .setDesc("student"));
        //click on save button
        app.getContact().clickOnSaveButton();
        //assert contact is added
        Assert.assertTrue(app.getContact().isContactCreated("Test"));
    }

    @Test(dataProvider = "addContact",dataProviderClass = DataProviders.class)
    public void addContactPositiveFrontDataProviderTest(String name, String lastName, String phone,
                                                        String email, String address, String description) {

        app.getContact().clickOnAddLink();

        app.getContact().fillAddContactForm(
                new Contact().setName(name)
                        .setLastname(lastName)
                        .setPhone(phone)
                        .setEmail(email)
                        .setAddress(address)
                        .setDesc(description));

        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactCreated(name));
    }

    @Test(dataProvider = "addContactFromCsvFile", dataProviderClass = DataProviders.class)
    public void addContactPositiveFromCsvFileTest(Contact contact) {

        app.getContact().clickOnAddLink();

        app.getContact().fillAddContactForm(contact);

        app.getContact().clickOnSaveButton();

//        Assert.assertTrue(app.getContact().isContactCreated(name));
    }

    @Test (dataProvider = "addContactFromNegativeCsvFile", dataProviderClass = DataProviders.class)
    public void addContactNegativeFromCsvFileTest(Contact contact) {

        app.getContact().clickOnAddLink();

        app.getContact().fillAddContactForm(contact);

        app.getContact().clickOnSaveButton();

        //assert Sign out button is displayed
        Assert.assertTrue(app.getContact().isAlertPresent());
    }

    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();
    }

}
