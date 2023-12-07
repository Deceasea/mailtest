package ru.decease.mailtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainTestClass {
    private static WebDriver driver;

    // Ваш логин и пароль
    String username = "irina.veselova";
    String password = "Dfghdjemk2345678";

    By loginButtonLocator = By.xpath("//button[contains(text(), 'Войти')]");
    By iframeLocator = By.className("ag-popup__frame__layout__iframe");
    By modalLocator = By.xpath("//*[@id=\"login-content\"]");
    By usernameInputLocator = By.name("username");
    By nextButtonLocator = By.xpath("//button[@data-test-id='next-button']");
    By passwordInputLocator = By.name("password");
    By submitButtonLocator = By.xpath("//button[@data-test-id='submit-button']");
    By writeMailLocator = By.xpath("//*[contains(text(), 'Написать письмо')]");
    By toInputLocator = By.xpath("//div[@data-type='to']//input");
    By subjectInputLocator = By.name("Subject");
    By bodyInputLocator = By.className("cke_editable");
    By saveButtonMailLocator = By.xpath("//button[@data-test-id='save']");
    By sendButtonMailLocator = By.xpath("//button[@data-test-id='send']");
    By closeModalMailLocator = By.xpath("//button[@title='Закрыть']");
    By avatarMailLocator = By.xpath("//div[@data-testid='whiteline-account']");
    By exitMailLocator = By.xpath("//div[@data-testid='whiteline-account-exit']");
    By deleteMailLocator = By.xpath("//span[@data-title-shortcut='Del']");

    public static String draftMailSubject = UUID.randomUUID().toString();

    @BeforeAll
    static void init() {
        createDriver();

        Assertions.assertDoesNotThrow(() -> driver.navigate().to("https://mail.ru/"),
                "Страница не доступна");
    }

//    @BeforeEach
//    void goTo() {
//        Assertions.assertDoesNotThrow(() -> driver.navigate().to("https://mail.ru/"),
//                "Страница не доступна");
//    }

    @AfterAll
    static void close() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getDriverWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(30L));
    }

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--profile-directory=Profile 1");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

}