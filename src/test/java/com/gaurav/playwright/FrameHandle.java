package com.gaurav.playwright;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class FrameHandle {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext brCx1 = browser.newContext();
        Page p1 = brCx1.newPage();
        brCx1.tracing().start(new Tracing.StartOptions().setSnapshots(true).setSnapshots(true));
        p1.navigate("https://www.londonfreelance.org/courses/frames/index.html");
        String header = p1.frameLocator("frame[name='main']").locator("h2").textContent();
        System.out.println("header is -> ");
        System.out.println(header);
        System.out.println("look second header");
        String h2 =  p1.frame("main").locator("h2").textContent();
        System.out.println(h2);

        brCx1.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("tracesQ1.zip")));
        p1.close();
        brCx1.close();
        browser.close();
        playwright.close();
    }
}
