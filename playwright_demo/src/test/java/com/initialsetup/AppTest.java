package com.initialsetup;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions;
import com.microsoft.playwright.options.AriaRole;

public class AppTest {

    Page page;
    Browser br;
    Playwright pw;

    @BeforeMethod
    public void setup() {
        pw = Playwright.create();
        br = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
        page = br.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
    }

    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(page.title());
        assertThat(page).hasTitle("\r\n" + //
                "EventHub — Discover & Book Events");
        page.getByPlaceholder("you@email.com").fill("rahulshetty1@yahoo.com");
        page.getByLabel("Password").fill("Magiclife1!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
        page.locator("#event-title-input").fill("FirstEvent");
        page.getByPlaceholder("Describe the event…").fill("This is the first event");
        page.getByLabel("Category").selectOption("Festival");
        page.locator("#city").fill("New York");
        page.getByLabel("Venue").fill("Madison Square Garden");
        page.getByLabel("Event Date & Time").fill("2026-11-26T20:00");
        page.getByPlaceholder("0.00").fill("100");
        page.getByPlaceholder("e.g. 500").fill("500");
        page.waitForTimeout(2000);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+ Add Event")).click();
        assertThat(page.getByText("Event Created")).isVisible();
        page.waitForTimeout(3000);
        page.locator("#nav-events").click();
        Locator allCards = page.getByTestId("event-card");
        System.out.println("Total cards: " + allCards.count());
        Locator TargetCard = allCards.filter(new Locator.FilterOptions().setHasText("FirstEvent"));
        assertThat(TargetCard).isVisible(new IsVisibleOptions().setTimeout(10000));
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        br.close();
        pw.close();
    }
}
