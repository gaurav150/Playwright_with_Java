package com.gaurav.playwright;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class TestPlayWrightAssertions {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();

        context.tracing().start(new Tracing.StartOptions().setSnapshots(true).setSnapshots(true));
        Page page = context.newPage();
        page.navigate("https://practicesoftwaretesting.com/contact");
        String pageTitle = page.title();
        String url = page.url();
        System.out.println("title is -> " + pageTitle);
        System.out.println("url is -> " + url);
        var firstName = page.getByLabel("First name");
        var lastName = page.getByLabel("Last name");
        var emailAddress = page.getByLabel("Email address");
        var messageLocator = page.getByLabel("Message");
        var subjectLocator = page.getByLabel("Subject");
        firstName.fill("amit");
        lastName.fill("biswal");
        emailAddress.fill("amit_biswal@gmail.com");
        String message = """
                Good Morning,
                
                Have you submitted the review form that we received via email?
                Thank you.""";

        subjectLocator.selectOption("Return");
        messageLocator.fill(message);

        assertThat(firstName).hasValue("amit");
        assertThat(lastName).hasValue("biswal");
        assertThat(emailAddress).hasValue("amit_biswal@gmail.com");
        assertThat(messageLocator).hasValue(message);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send")).click();
        String textMessage = page.locator(".alert-success").textContent();
        Assertions.assertTrue(textMessage.contains("Thanks for your message"));
        System.out.println("text is -> " + textMessage);
        page.pause();
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
        browser.close();
        playwright.close();
    }
}

