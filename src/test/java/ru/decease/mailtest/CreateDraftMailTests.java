package ru.decease.mailtest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Epic("Scenario 1")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateDraftMailTests extends MainTestClass {

    private static final String mailTo = draftMailSubject + "@gmail.ru";
    private static final String mailSubject = "Subject: " + draftMailSubject;
    private static final String mailBody = "Body: " + draftMailSubject;

    @Feature("Draft Mail")
    @Description("Login")
    @Test
    @Order(5)
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
    @Description("Create mail")
    public void testCreateMail() {
        WebElement writeMailButton = getDriver().findElement(writeMailLocator);
        writeMailButton.click();

        WebElement toInputField = getDriver().findElement(toInputLocator);
        toInputField.sendKeys(mailTo);

        WebElement subjectInputField = getDriver().findElement(subjectInputLocator);
        subjectInputField.sendKeys(mailSubject);

        WebElement bodyInputField = getDriver().findElement(bodyInputLocator).findElement(By.tagName("div"));
        bodyInputField.click();
        bodyInputField.sendKeys(mailBody);

        WebElement saveMailButton = getDriver().findElement(saveButtonMailLocator);
        saveMailButton.click();

        WebElement closeModalMailButton = getDriver().findElement(closeModalMailLocator);
        closeModalMailButton.click();

        getDriverWait().until(ExpectedConditions.numberOfElementsToBe(By.className("compose-app__compose"), 0));
    }

    @Test
    @Order(15)
    @Description("Draft mail")
    public void testFindDraftMail() {
        getDriver().get("https://e.mail.ru/drafts/");

        WebElement draftMail = getDriver().findElement(By.xpath("//*[contains(text(), '" + draftMailSubject + "')]"));
        draftMail.click();

        WebElement draftMailTo = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailTo + "')]"));
        Assertions.assertTrue(draftMailTo.isDisplayed());

        WebElement draftMailSubject = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailSubject + "')]"));
        Assertions.assertTrue(draftMailSubject.isDisplayed());

        WebElement draftMailBody = getDriver().findElement(By.xpath("//*[contains(text(), '" + mailBody + "')]"));
        Assertions.assertTrue(draftMailBody.isDisplayed());
    }

    @Test
    @Order(20)
    @Description("Send draft")
    public void testSendDraftMail() {
        WebElement sendButtonMailButton = getDriver().findElement(sendButtonMailLocator);
        sendButtonMailButton.click();

        getDriverWait().until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[contains(text(), '" + draftMailSubject + "')]"), 0));
    }

    @Test
    @Order(25)
    @Description("Check sent mail")
    public void testSentMail() {
        getDriver().get("https://e.mail.ru/sent/");

        WebElement sentMail = getDriver().findElement(By.xpath("//*[contains(text(), '" + draftMailSubject + "')]"));
        Assertions.assertTrue(sentMail.isDisplayed());
    }

    @Test
    @Order(30)
    @Description("Exit from service")
    public void testExitFromMail() {
        WebElement avatarMailButton = getDriver().findElement(avatarMailLocator);
        avatarMailButton.click();

        WebElement exitMailButton = getDriver().findElement(exitMailLocator);
        exitMailButton.click();
    }
}
