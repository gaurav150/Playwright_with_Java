package com.gaurav.playwright;


import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class TestPlayWrightPracticeElements {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext brCx1 = browser.newContext();
        Page p1 = brCx1.newPage();
        brCx1.tracing().start(new Tracing.StartOptions().setSnapshots(true).setSnapshots(true));
//        p1.navigate("https://rahulshettyacademy.com/client/#/auth/login");
//        p1.navigate("https://academy.naveenautomationlabs.com/");
        p1.navigate("https://orangehrm.com/30-day-free-trial");
//        Locator email = p1.getByPlaceholder("email@example.com");
//        Locator password = p1.getByPlaceholder("enter your passsword");
//        Locator login =  p1.locator("text = Login");
//        email.fill("jai@gmail.com");
//        password.fill("1233");
//        login.hover();
//        login.click();
        Locator links  = p1.locator("text=Privacy Policy");
        System.out.println("count is -> "+links.count());
        links.first().click();
//        Locator loginBtn = p1.locator("text = Login");
//        loginBtn.hover();
//        loginBtn.click();
        brCx1.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("traces.zip")));
        p1.close();
        brCx1.close();
        browser.close();
        playwright.close();
    }
}
