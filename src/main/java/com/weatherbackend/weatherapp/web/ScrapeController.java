package com.weatherbackend.weatherapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ResponseBody
public class ScrapeController {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/airport")
    public String getAirportData() {

        WebDriver driver = new ChromeDriver();    

        String projectPath = System.getProperty("user.dir");
        String driverPath = projectPath + "\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        String headingText;
        String paragraphText;

        try {
			driver.get("http://example.com");

			WebElement heading = driver.findElement(By.tagName("h1"));
			WebElement paragraph = driver.findElement(By.tagName("p"));

			headingText = heading.getText();
			paragraphText = paragraph.getText();
		} finally {
			driver.quit();
		}

        return headingText + "," + paragraphText;
    }
    
}
