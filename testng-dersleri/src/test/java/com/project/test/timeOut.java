package com.project.test;

import com.project.test.BaseTest;
import org.testng.annotations.Test;

public class timeOut extends BaseTest {
    @Test(timeOut = 8000)
    public void loading(){
        driver.get("https://www.n11.com/");
    }
}
