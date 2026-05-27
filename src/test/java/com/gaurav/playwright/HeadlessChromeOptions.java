package com.gaurav.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

import java.util.Arrays;

public class HeadlessChromeOptions implements OptionsFactory {
    @Override
    public Options getOptions() {
        return new Options().setLaunchOptions(new BrowserType.LaunchOptions()
                        .setArgs(Arrays.asList(
                                "--start-maximized",
                                "--no-sandbox"))   // keep only if you need it (often Linux/CI)
                )
                .setContextOptions(new Browser.NewContextOptions()
                                .setViewportSize(null))
                .setHeadless(false)
                .setTestIdAttribute("data-test");
    }

}
