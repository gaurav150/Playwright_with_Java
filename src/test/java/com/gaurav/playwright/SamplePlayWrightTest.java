package com.gaurav.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SamplePlayWrightTest {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void setUp() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
    }

    @AfterEach
    void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    void shouldShowTheTitlePage() {

        String url = "https://practicesoftwaretesting.com/";
        page.navigate(url);
        String title = page.title();
        System.out.println("title of the web page is -> ");
        System.out.println(title);

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }

    @Test
    void shouldSearchByKeyWord() {

        String url = "https://practicesoftwaretesting.com/";
        page.navigate(url);
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int matchingSearchResults = page.locator(".card").count();
        Assertions.assertTrue(matchingSearchResults > 0, "No Search Results found");
        page.pause();
    }
}
