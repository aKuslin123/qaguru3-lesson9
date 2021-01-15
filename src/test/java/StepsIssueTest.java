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

public class StepsIssueTest {
    private final static String USER = "aKuslin123";
    private final static String PASS = "d8W4y8mg";
    private final static String ISSUE = "Issue1";
    private final static String LABEL = "bug";
    private final static String ASSIGNEES = "aKuslin123";

    @Test
    public void createIssue() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Проходим авторизацию", (step) -> {
            step.parameter("login", USER);
            step.parameter("pass", PASS);

            $("a[href='/login']").click();
            sleep(1000);
            $("#login_field").sendKeys(USER);
            $("#password").sendKeys(PASS);
            $(byValue("Sign in")).click();
        });

        step("Переходим в репозиторий", () -> {
            $(byText("qaguru3-lesson5")).click();
            $("input[id='empty-setup-clone-url']").shouldHave(value("https://github.com/aKuslin123/qaguru3-lesson5.git"));
        });

        step("Переходим в issue", () -> {
            $("[data-content='Issues']").click();
        });

        step("Создаем issue = " + ISSUE, (step) -> {
            step.parameter("issue", ISSUE);

            $x("//span[contains(text(), 'New issue')]").click();
            $("#issue_title").sendKeys(ISSUE);
        });

        step("Назначаем issue, assignees = " + ASSIGNEES, (step) -> {
            step.parameter("assignees", ASSIGNEES);

            $(byText("Assignees")).click();
            $$(".js-username").findBy(text(ASSIGNEES)).click();
            $("#assignee-filter-field").pressEscape();
        });

        step("Выбираем тег для issue, тег = " + LABEL, (step) -> {
            step.parameter("label", LABEL);

            $(byText("Labels")).click();
            $$(".name").findBy(text(LABEL)).click();
            $("#label-filter-field").pressEscape();
        });

        step(("Кликаем по кнопке 'Submit new issue'"), () -> {
            $(byText("Submit new issue")).click();
        });

        step(("Закрываем issue"), () -> {
            $(byText("Close issue")).click();
        });
    }
}
