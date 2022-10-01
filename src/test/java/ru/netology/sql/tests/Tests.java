package ru.netology.sql.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void shouldMustPassVerification() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(SQLHelper.getAuthInfo());
        DashboardPage dashboardPage = verificationPage.validVarify(SQLHelper.getVerificationCode());
        dashboardPage.shouldVisible();
    }
}
