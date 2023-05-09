package com.tutorialsninja.testsuites;


import com.tutorialsninja.customlisteners.CustomListeners;
import com.tutorialsninja.pages.AccountRegisterPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.MyAccountPage;
import com.tutorialsninja.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(CustomListeners.class)
public class AccountRegisterPageTest extends BaseTest {
    HomePage homePage;
    AccountRegisterPage accountRegisterPage;
    MyAccountPage accountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        accountPage = new MyAccountPage();
        accountRegisterPage = new AccountRegisterPage();
    }

    @Test(groups = {"sanity","regression"})
    public void verifyUserShouldNavigateToLoginPageSuccessfully() throws InterruptedException {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Register");
        Assert.assertEquals(accountRegisterPage.getRegisterAccountText(),
                "Register Account", "Register page not displayed");
    }

    @Test(groups = {"smoke","regression"})
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Register");
        accountRegisterPage.enterFirstName("DP" + getAlphaNumericString(4));
        accountRegisterPage.enterLastName("Patel" + getAlphaNumericString(4));
        accountRegisterPage.enterEmail("DPPatel" + getAlphaNumericString(4) + "@gmail.com");
        accountRegisterPage.enterTelephone("07898765433");
        accountRegisterPage.enterPassword("AimHigh123");
        accountRegisterPage.enterConfirmPassword("AimHigh123");
        accountRegisterPage.selectSubscription("Yes");
        accountRegisterPage.clickOnPrivacyPolicyCheckBox();
        accountRegisterPage.clickOnContinueButton();
        Assert.assertEquals(accountPage.getYourAccountHasBeenCreatedText(), "Your Account Has Been Created!",
                "Account not created");
        accountPage.clickOnContinueButton();
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Logout");
        Assert.assertEquals(accountPage.getAccountLogoutText(), "Account Logout", "Not logged out");
        accountPage.clickOnContinueButton();
    }

}
