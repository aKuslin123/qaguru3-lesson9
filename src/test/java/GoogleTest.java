import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class GoogleTest {

    @Test
    void searchTest() {
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
}