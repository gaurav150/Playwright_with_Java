package com.gaurav.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(HeadlessChromeOptions.class)
public class TestPlayWrightForm {


    @DisplayName("Locating elements by placeholders and labels")
    @Nested
    class LocatingElementsByPlaceholdersAndLabels {

        @DisplayName("Using a label")
        @Test
        void byLabel(Page page) {
            page.navigate("https://practicesoftwaretesting.com/contact");
            page.pause();

            page.getByLabel("First name").fill("Obi-Wan");
            page.getByLabel("Last name").fill("Kenobi");
            page.getByLabel("Email address").fill("obi-wan@kenobi.com");
            page.getByLabel("Subject").selectOption(new SelectOption().setLabel("Customer service"));
            page.getByLabel("Message *").fill("Hello there");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send"));
        }

        @DisplayName("Using a placeholder text")
        @Test
        void byPlaceholder(Page page) {
            page.navigate("https://practicesoftwaretesting.com/contact");
            page.pause();

            page.getByPlaceholder("Your first name").fill("Obi-Wan");

            page.getByPlaceholder("Your last name").fill("Kenobi");
            page.getByPlaceholder("Your email").fill("obi-wan@kenobi.com");
            page.getByLabel("Subject").selectOption(new SelectOption().setLabel("Customer service"));
            page.getByLabel("Message *").fill("Hello there");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send"));
        }

        @DisplayName("Input Fields")
        @Test
        void fieldValues(Page page) {
            page.pause();
            page.navigate("https://practicesoftwaretesting.com/contact");
            var firstFieldName = page.getByLabel("First name");
            firstFieldName.fill("alex");
            assertThat(firstFieldName).hasValue("alex");
        }

        @DisplayName("Complete Form")
        @Test
        void completeForm(Page page) throws URISyntaxException {
            page.pause();
            page.navigate("https://practicesoftwaretesting.com/contact");
            var firstFieldName = page.getByLabel("First name");
            var lastFieldName = page.getByLabel("Last name");
            var emailAddress = page.getByLabel("Email address");
            var messageField = page.getByLabel("Message");
            var subject = page.getByLabel("Subject");
            var upload = page.getByLabel("Attachment");
            var submitBtn = page.locator(".btnSubmit");


            String message = "Coding daily builds logic, confidence, and creativity. daily";
            firstFieldName.fill("alex");
            lastFieldName.fill("Jones");
            emailAddress.fill("alex@gmail.com");
            messageField.fill(message);
            subject.selectOption("Warranty");

            Path fileToUpload = Paths.get(
                    Objects.requireNonNull(
                            ClassLoader.getSystemResource("Data/Sample-test.txt")
                    ).toURI()
            );
            page.setInputFiles("#attachment", fileToUpload);


            assertThat(firstFieldName).hasValue("alex");
            assertThat(lastFieldName).hasValue("Jones");
            assertThat(emailAddress).hasValue("alex@gmail.com");
            assertThat(messageField).hasValue(message);
            String uploadedFiles = upload.inputValue();
            org.assertj.core.api.Assertions.assertThat(uploadedFiles).endsWith("Sample-test.txt");
            submitBtn.click();

        }

        @DisplayName("Mandatory Fields")
        @ParameterizedTest
        @ValueSource(strings = {"First name", "Last name", "Email", "Message"})
        void mandatoryFields(String fieldName, Page page) {
            page.pause();
            page.navigate("https://practicesoftwaretesting.com/contact");

            var firstFieldName = page.getByLabel("First name");
            var lastFieldName = page.getByLabel("Last name");
            var emailAddress = page.getByLabel("Email address");
            var messageField = page.getByLabel("Message");
            var submitBtn = page.locator(".btnSubmit");

            String message = "Coding daily builds logic, confidence, and creativity. daily";
            firstFieldName.fill("alex");
            lastFieldName.fill("Jones");
            emailAddress.fill("alex@gmail.com");
            messageField.fill(message);

            // clear one of the fields
            page.getByLabel(fieldName).clear();

            submitBtn.click();
            var errorMessage = page.getByRole(AriaRole.ALERT).getByText(fieldName + " is required");
            assertThat(errorMessage).isVisible();
        }
    }

}
