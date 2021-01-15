import io.qameta.allure.*;
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
import static io.qameta.allure.Allure.step;

public class AnnotationIssueTest {
    private final static String USER = "aKuslin123";
    private final static String PASS = "d8W4y8mg";
    private final static String ISSUE = "Issue1";
    private final static String LABEL = "bug";
    private final static String ASSIGNEES = "aKuslin123";

    @Test
    @DisplayName("Тест с аннотациями, создание Issue")
    @Feature("Issues")
    @Story("User should create issues in existing repository")
    @Link(url = "https://github.com", name = "Тест")
    @Severity(SeverityLevel.CRITICAL)
    public void createIssue() {
        final BaseSteps steps = new BaseSteps();
        steps.openMainPage();
        steps.logIntoAccount(USER, PASS);
        steps.goToRepository();
        steps.goToIssue();
        steps.createIssue(ISSUE);
        steps.assigneesForIssue(ASSIGNEES);
        steps.tagForIssue(LABEL);
        steps.clickSubmit();
        steps.closeIssue();
    }

    public static class BaseSteps {

        @Step("Открываем главную страницу")
        public void openMainPage() {
            open("https://github.com");
        }

        @Step("Проходим авторизацию {USER} {PASS}")
        public void logIntoAccount(final String USER, final String PASS) {
            $("a[href='/login']").click();
            sleep(1000);
            $("#login_field").sendKeys(USER);
            $("#password").sendKeys(PASS);
            $(byValue("Sign in")).click();
        }

        @Step("Переходим в репозиторий")
        public void goToRepository() {
            $(byText("qaguru3-lesson5")).click();
            $("input[id='empty-setup-clone-url']").shouldHave(value("https://github.com/aKuslin123/qaguru3-lesson5.git"));
        }

        @Step("Переходим в issue")
        public void goToIssue() {
            $("[data-content='Issues']").click();
        }


        @Step("Создаем issue = {ISSUE}")
        public void createIssue(final String ISSUE) {
            $x("//span[contains(text(), 'New issue')]").click();
            $("#issue_title").sendKeys(ISSUE);
        }

        @Step("Назначаем issue, assignees = {ASSIGNEES}")
        public void assigneesForIssue(final String ASSIGNEES) {
            $(byText("Assignees")).click();
            $$(".js-username").findBy(text(ASSIGNEES)).click();
            $("#assignee-filter-field").pressEscape();
        }

        @Step("Выбираем тег для issue, тег = {LABEL}")
        public void tagForIssue(final String LABEL) {
            $(byText("Labels")).click();
            $$(".name").findBy(text(LABEL)).click();
            $("#label-filter-field").pressEscape();
        }

        @Step("Кликаем по кнопке 'Submit new issue'")
        public void clickSubmit() {
            $(byText("Submit new issue")).click();
        }

        @Step("Закрываем issue")
        public void closeIssue() {
            $(byText("Close issue")).click();
        }
    }
}
