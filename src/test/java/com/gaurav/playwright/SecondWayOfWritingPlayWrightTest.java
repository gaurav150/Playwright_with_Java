package com.gaurav.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@UsePlaywright(SecondWayOfWritingPlayWrightTest.CustomOptions.class)
class SecondWayOfWritingPlayWrightTest {

    public static class CustomOptions implements OptionsFactory {
        @Override
        public Options getOptions() {
            return new Options()
                    .setHeadless(false)
                    .setLaunchOptions(
                            new BrowserType.LaunchOptions()
                                    .setArgs(Arrays.asList(
                                            "--start-maximized",
                                            "--no-sandbox"))   // keep only if you need it (often Linux/CI)
                    )
                    .setContextOptions(
                            new Browser.NewContextOptions()
                                    .setViewportSize(null)   // important: don’t use default 1280×720
                    );
        }
    }


    @Test
    void shouldShowTheTitlePage(Page page) {

        String url = "https://practicesoftwaretesting.com/";
        page.navigate(url);
        String title = page.title();
        System.out.println("title of the web page is -> ");
        System.out.println(title);

        Assertions.assertTrue(title.contains("Practice Software Testing"));

    }

    @Test
    void shouldSearchByKeyWord(Page page) {

        String url = "https://practicesoftwaretesting.com/";
        page.navigate(url);
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int matchingSearchResults = page.locator(".card").count();
        Assertions.assertTrue(matchingSearchResults > 0, "No Search Results found");
        page.pause();


    }
}
