package com.gaurav.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SamplePlayWrightTest {

    @Test
    void shouldShowTheTitlePage() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        String url = "https://practicesoftwaretesting.com/";
        page.navigate(url);
        String title =  page.title();
        System.out.println("title of the web page is -> ");
        System.out.println(title);

        Assertions.assertTrue(title.contains("Practice Software Testing"));
        browser.close();
    }
}
