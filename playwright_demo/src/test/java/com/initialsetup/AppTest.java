package com.initialsetup;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        Playwright pw = Playwright.create();
        Browser br = pw.chromium().launch();
        
    }
}
