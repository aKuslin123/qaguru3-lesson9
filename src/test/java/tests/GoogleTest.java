package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;
import static io.qameta.allure.Allure.step;

public class GoogleTest {

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.startMaximized = true;
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());

        closeWebDriver();
    }

    @Test
    @DisplayName("Successful google test")
    void successfulSearchTest() {
        step("Открываем google.com", () -> {
            open("https://google.com");
        });

        step("Вводим Selenide в поиск", () -> {
            $(byName("q")).setValue("Selenide").pressEnter();
        });

        step("Проверяем что Selenide появился в рез. поиска", () -> {
            $("html").shouldHave(Condition.text("Selenide: лаконичные и стабильные UI тесты на Java"));
        });
    }

    @Test
    @DisplayName("Unsuccessful google test")
    void unsuccessfulSearchTest() {
        step("Открываем google.com", () -> {
            open("https://google.com");
        });

        step("Вводим Selenide в поиск", () -> {
            $(byName("q")).setValue("Selenide").pressEnter();
        });

        step("Проверяем что Selenide появился в рез. поиска", () -> {
            $("html").shouldHave(Condition.text("1Selenide: лаконичные и стабильные UI тесты на Java"));
        });
    }
}