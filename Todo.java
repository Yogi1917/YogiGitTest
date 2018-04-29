package zendesk;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Todo {

    WebDriver driver;
	@Test
	@Parameters("BrowserName")
	
	public void TodoPageTest(String browser) throws Exception {
		//Launching firfox browser
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "/Users/shubyog/Documents/workspace/Drivers/geckodriver");/*Change the path of geckodriver as per location on your laptop/machine*/
			 driver = new FirefoxDriver();
			
		}
		//Launching chrome browser
		else if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "/Users/shubyog/Documents/workspace/Drivers/chromedriver");/*Change the path of chromedriver as per location on your laptop/machine*/
			driver= new ChromeDriver();
		}
		
		
		//Opening the web page
		driver.get("http://todomvc.com");
		
		//Click on ember.js 
		WebElement ember=driver.findElement(By.xpath("//a[@href='examples/emberjs']"));
		    ember.click();
		
		//Making the thread sleep so that page loads all the elements before performing any other action
		Thread.sleep(2000);
		
//1) I want to add a Todo item
		WebElement item=driver.findElement(By.xpath("//input[@id='new-todo']"));
		    item.click();
		    item.sendKeys("Zendesk");
		    String str=item.getAttribute("value");
		    item.sendKeys(Keys.ENTER);		
		
//2) I want to edit the content of an existing Todo item
		WebElement addedtask=driver.findElement(By.xpath("//input[@type='checkbox'][@class='toggle']"));
	        addedtask.click();
	    
	    //javascript to perform double click to edit todo task
	    WebElement edittask=driver.findElement(By.xpath("//label"));
	        String jsCode = "var evObj = new MouseEvent('dblclick', {bubbles: true, cancelable: true, view: window});";
	        jsCode += " arguments[0].dispatchEvent(evObj);";
	        ((JavascriptExecutor) driver).executeScript(jsCode, edittask);
	        ((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", edittask);
	    
	    //for loop for deleting the todo task entered in steps above
	    for(int i=0;i<str.length();i++){
	       driver.findElement(By.xpath("//input[@class='edit']")).sendKeys(Keys.BACK_SPACE);
	    }
	    
	    //Enter new todo task to complete editing 
	    WebElement editedtext= driver.findElement(By.xpath("//input[@class='edit']"));
	    	editedtext.sendKeys("Edited Zendesk Task");
	           editedtext.sendKeys(Keys.ENTER);
	      	 
	
//3) I can complete a Todo by clicking inside the circle UI to the left of the Todo	    
	    WebElement completedtask=driver.findElement(By.xpath("//input[@type='checkbox'][@class='toggle']"));
	      completedtask.click();
	    
//4) I can re-activate a completed Todo by clicking inside the circle UI
	    WebElement completedtask1=driver.findElement(By.xpath("//input[@type='checkbox'][@class='toggle']"));
	       completedtask1.click();
	    
//5) I can add a second Todo	    
	    WebElement newitem2= driver.findElement(By.xpath("//input[@id='new-todo']"));
	      newitem2.sendKeys("ZendeskTask");
	      /*String str1=newitem2.getAttribute("value");
	      //System.out.println(str);*/
	      newitem2.sendKeys(Keys.ENTER);
	    
//6) I can complete all active Todos by clicking the down arrow at the top-left of the UI 
	    WebElement selectall=driver.findElement(By.xpath("//input[@id='toggle-all'][@type='checkbox']"));
	      selectall.click();
	    
//7) I can filter the visible Todos by Completed state	    
	    WebElement completedstate=driver.findElement(By.xpath("//a[@href='#/completed']"));
	      completedstate.click();
	    
//8) I can clear a single Todo item from the list completely by clicking the Close icon
	      if(browser.equalsIgnoreCase("Chrome")){
	     List<WebElement> lables=driver.findElements(By.xpath("//label"));
         int x=lables.size();
          lables.get(x-1).click();
        
        List<WebElement> close = driver.findElements(By.xpath("//button[@class='destroy']"));
	       int y=close.size();
	        close.get(y-1).click();
	      }
//9) I can clear all completed Todo items from the list completely
	        WebElement clearall=driver.findElement(By.xpath("//button[@id='clear-completed']"));
 	        clearall.click();
	    
 	        driver.quit();
	}

}
