package com.phonebook.fw;

import com.phonebook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void fillAddContactForm(Contact contact) {
        type(By.cssSelector("input:nth-child(1)"), contact.getName()); //+i
        type(By.cssSelector("input:nth-child(2)"), contact.getLastname());
        type(By.cssSelector("input:nth-child(3)"), contact.getPhone());
        type(By.cssSelector("input:nth-child(4)"), contact.getEmail());
        type(By.cssSelector("input:nth-child(5)"), contact.getAddress());
        type(By.cssSelector("input:nth-child(6)"), contact.getDesc());
    }

    public void clickOnAddLink() {
        click(By.xpath("//a[.='ADD']"));
    }

    public boolean isContactCreated(String text){
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element: contacts ) {
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }

    public void removeContact() {
        if (!isContactListEmpty()) {
            click(By.cssSelector(".contact-item_card__2SOIM"));
            click(By.xpath("//button[.='Remove']"));
        }
    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    public void openContactForm(String nameOfCard) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(nameOfCard)) {
                element.click();
            }
        }
    }


    public boolean findElementAfterRemove(String nameOfCard) {
        if (isElementPresent(By.xpath("//h1[contains(text(),'No Contacts here!')]"))) {
            return true;
        }
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(nameOfCard)) {
                return false;
            }
        }
        return true;
    }

    public void clickOnRemoveButton() {
        click(By.xpath("//button[contains(text(),'Remove')]"));
    }

    public int sizeOfContacts() {
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
        return 0;
    }

    public void cleanAllContactFrom() {
        if (sizeOfContacts() != 0) {
            for (int i = 0; i < sizeOfContacts(); i++) {
                click(By.cssSelector(".contact-item_card__2SOIM"));
                clickOnRemoveButton();
            }
        }
    }
}