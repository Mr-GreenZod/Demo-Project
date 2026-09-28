package com.initialsetup;

import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        Playwright pw = Playwright.create();
        Browser br = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)    );
        Page page = br.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        System.out.println(page.title());
        assertThat(page).hasTitle("\r\n" + //
                        "EventHub — Discover & Book Events"); 
                        page.getByPlaceholder("you@email.com").fill("chadharyabhishek8888@gmai.com");
                        page.getByLabel("Password").fill("Abhi@123");
                        
    }
}
