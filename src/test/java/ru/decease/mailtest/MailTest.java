package ru.decease.mailtest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class MailTest extends MainTestClass {

    @Test
    @Order(2)
    public void testCreateMail() {
        WebElement writeMailButton = getDriver().findElement(writeMailLocator);
        writeMailButton.click();

        WebElement toInputField = getDriver().findElement(toInputLocator);
        toInputField.sendKeys(draftMailSubject + "@mail.ru");

        WebElement subjectInputField = getDriver().findElement(subjectInputLocator);
        subjectInputField.sendKeys(draftMailSubject);

        WebElement bodyInputField = getDriver().findElement(bodyInputLocator);
        bodyInputField.click();
        bodyInputField.sendKeys(draftMailSubject);
    }
}
