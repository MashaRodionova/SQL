package ru.netology.sql.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.SQLHelper;
import ru.netology.sql.page.DashboardPage;
import ru.netology.sql.page.LoginPage;
import ru.netology.sql.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class Tests {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldPassVerification1() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validAuthInfo(DataHelper.getValidAuthInfo());
        DashboardPage dashboardPage = verificationPage.validVarify(SQLHelper.getVerificationCode());
        dashboardPage.shouldVisible();
    }

    @Test
    void shouldPassVerification2() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validAuthInfo(DataHelper.getValidOtherAuthInfo());
        DashboardPage dashboardPage = verificationPage.validVarify(SQLHelper.getVerificationCode());
        dashboardPage.shouldVisible();
    }

    @Test
    void shouldTrowErrorMessageAfterInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.invalidAuthInfo(DataHelper.getInvalidAuthInfo());
        loginPage.findErrorMessage("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void shouldBlockedPageAfterThreefoldInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.invalidThreefoldPassword(DataHelper.getInvalidAuthInfo());
        loginPage.findPopUp("Вы превысили лимит попыток ввода. Возможность ввода возобновится через 1 минуту");
    }

    @Test
    void shouldTrowErrorAfterInvalidCode() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validAuthInfo(DataHelper.getValidOtherAuthInfo());
        verificationPage.invalidVarify(DataHelper.generateFakerCode());
        verificationPage.findErrorMessage("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }
}
