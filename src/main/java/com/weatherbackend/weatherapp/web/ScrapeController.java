package com.weatherbackend.weatherapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;

@Controller
@ResponseBody
public class ScrapeController {

    @Value("${AIRPORT_CODE}")
    String airport = null;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/airport")
    public String getAirportData() {

        WebDriver driver = new ChromeDriver();    

        String projectPath = System.getProperty("user.dir");
        String driverPath = projectPath + "\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        String elementText;
        String finalText = "";

        try {
			driver.get("https://lentopaikat.fi/" + airport + "/?doing_wp_cron=1727771104.7144129276275634765625");

            String pathExpression = "//p[contains(strong, 'Koordinaatit:')]";
            WebElement element = driver.findElement(By.xpath(pathExpression));
            elementText = element.getText();

            String regex = "(\\d{2}/\\d{2})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(elementText);

            while (matcher.find()) {
                String dimension = matcher.group(1);
                finalText+= dimension + " ";
            }
            
		} finally {
			driver.quit();
		}

        return finalText;
    }
    
}
