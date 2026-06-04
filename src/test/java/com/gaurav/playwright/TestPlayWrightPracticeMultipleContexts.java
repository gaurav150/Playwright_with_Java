package com.gaurav.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import java.nio.file.Paths;

public class TestPlayWrightPracticeMultipleContexts {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext brCx1 = browser.newContext();
        Page p1 = brCx1.newPage();
        brCx1.tracing().start(new Tracing.StartOptions().setSnapshots(true).setSnapshots(true));
        p1.navigate("https://rahulshettyacademy.com/client/#/auth/login");
        var email = p1.getByPlaceholder("email@example.com");
        var password = p1.getByPlaceholder("enter your passsword");
        email.fill("jai@gmail.com");
        password.fill("1233");

        brCx1.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("traces.zip")));
        p1.close();
        brCx1.close();
        browser.close();
        playwright.close();
    }
}
