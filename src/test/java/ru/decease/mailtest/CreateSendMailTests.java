package ru.decease.mailtest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Epic("Scenario 2")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateSendMailTests extends MainTestClass {

    private static final String mailTo = draftMailSubject + "@gmail.ru";
    private static final String mailSubject = "Subject: " + draftMailSubject;
    private static final String mailBody = "Body: " + draftMailSubject;

    @Feature("Steps to check scenario")
    @Test
    @Order(5)
    @Description("Login")
    public void testLoginToMailRu() {
        // Нажатие кнопки входа
        WebElement loginButton = getDriver().findElement(loginButtonLocator);
        loginButton.click();

        // Найдите iframe по его идентификатору, имени или другому атрибуту
        WebElement iframe = getDriver().findElement(iframeLocator);

        // Переключитесь на iframe
        getDriver().switchTo().frame(iframe);

        WebElement modal = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(modalLocator));

        // Заполнение полей логина и пароля
        WebElement loginField = modal.findElement(usernameInputLocator);
        loginField.sendKeys(username);

        // Нажатие кнопки Ввести пароль
        WebElement nextButton = getDriver().findElement(nextButtonLocator);
        nextButton.click();

        WebElement passwordField = modal.findElement(passwordInputLocator);
        passwordField.sendKeys(password);

        // Нажатие кнопки входа после ввода логина и пароля
        WebElement submitButton = getDriver().findElement(submitButtonLocator);
        submitButton.click();

        // Ждем, пока загрузится страница
        WebElement successTextElement = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(writeMailLocator));

        // Проверка успешного входа (здесь может потребоваться адаптация в зависимости от конкретной верстки страницы)
        Assertions.assertTrue(successTextElement.isDisplayed());
    }

    @Test
    @Order(10)
    @Description("Crate mail")
    public void testCreateMail() {
        WebElement writeMailButton = getDriver().findElement(writeMailLocator);
        writeMailButton.click();

        getDriverWait().until(ExpectedConditions.numberOfElementsToBe(toInputLocator, 1));
        WebElement toInputField = getDriver().findElement(toInputLocator);
        toInputField.sendKeys(username + "@mail.ru");

        WebElement subjectInputField = getDriver().findElement(subjectInputLocator);
        subjectInputField.sendKeys(mailSubject);

        WebElement bodyInputField = getDriver().findElement(bodyInputLocator).findElement(By.tagName("div"));
        bodyInputField.click();
        bodyInputField.sendKeys(mailBody);

        WebElement sendMailButton = getDriver().findElement(sendButtonMailLocator);
        sendMailButton.click();

        getDriverWait().until(ExpectedConditions.numberOfElementsToBe(By.className("compose-app__compose"), 0));
    }

    @Test
    @Order(15)
    @Description("Find mail")
    public void testFindInboxMail() {
        getDriver().get("https://e.mail.ru/tomyself/");

        WebElement sentMail = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailSubject + "')]"));
        sentMail.click();

        WebElement sentMailTo = getDriver().findElement(By.xpath("//*[contains(text(), '" + username + "@mail.ru')]"));
        Assertions.assertTrue(sentMailTo.getText().contains("Ирина Веселова"));

        WebElement sentMailSubject = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailSubject + "')]"));
        Assertions.assertTrue(sentMailSubject.isDisplayed());

        WebElement sentMailBody = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailBody + "')]"));
        Assertions.assertTrue(sentMailBody.isDisplayed());
    }

    @Test
    @Order(20)
    @Description("Delete mail")
    public void testDeleteInboxMail() {
        WebElement deleteMailButton = getDriver().findElement(deleteMailLocator);
        deleteMailButton.click();

    }

    @Test
    @Order(25)
    @Description("Check trash")
    public void testCheckTrash() {
        getDriver().get("https://e.mail.ru/trash/");

        WebElement sentMail = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailSubject + "')]"));
        sentMail.click();

        WebElement sentMailTo = getDriver().findElement(By.xpath("//*[contains(text(), '" + username + "@mail.ru')]"));
        Assertions.assertTrue(sentMailTo.getText().contains("Ирина Веселова"));

        WebElement sentMailSubject = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailSubject + "')]"));
        Assertions.assertTrue(sentMailSubject.isDisplayed());

        WebElement sentMailBody = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailBody + "')]"));
        Assertions.assertTrue(sentMailBody.isDisplayed());
    }

    @Test
    @Order(30)
    @Description("Exit from serrvice")
    public void testExitFromMail() {
        WebElement avatarMailButton = getDriver().findElement(avatarMailLocator);
        avatarMailButton.click();

        WebElement exitMailButton = getDriver().findElement(exitMailLocator);
        exitMailButton.click();
    }
}
