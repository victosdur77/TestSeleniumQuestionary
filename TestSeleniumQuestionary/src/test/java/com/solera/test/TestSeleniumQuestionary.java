package com.solera.test;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSeleniumQuestionary {

	private WebDriver driver; //Instanciamos l web driver y le damos nombre
	
	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup(); //Le damos el ejecutable de chrome driver.
		driver = new ChromeDriver(); //instaciamos el web driver ahora si como un navegador
		driver.manage().window().maximize(); // lo maximizamos
		driver.get("https://localhost:44446/");
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verificarPaginaInicial() {
		String url = "https://localhost:44446/";
		int nLinks = 4;
		Assert.assertEquals(url, driver.getCurrentUrl());
		List<WebElement> botones = driver.findElements(By.tagName("a"));
		
		
		Assert.assertEquals(nLinks,botones.size());
		Assert.assertEquals("Cuestionario",botones.get(0).getText());
		Assert.assertEquals("Login",botones.get(1).getText());
		Assert.assertEquals("Register",botones.get(2).getText());
		Assert.assertEquals("Questionary",botones.get(3).getText());
		
	}
	
	@Test
	public void verificarLoginClick() throws InterruptedException {
		WebElement LoginButton = driver.findElement(By.linkText("Login"));
		LoginButton.click();
		Thread.sleep(2000);
		String url = "https://localhost:44446/signup";
		Assert.assertEquals(url, driver.getCurrentUrl());
		WebElement signup = driver.findElement(By.tagName("h2"));
		Assert.assertEquals("Sign Up", signup.getText());
		Assert.assertTrue(signup.isDisplayed());
	}
	
	@Test
	public void verificarBotonCancelarLogin() throws InterruptedException {
		WebElement LoginButton = driver.findElement(By.linkText("Login"));
		LoginButton.click();
		Thread.sleep(2000);
		String url = "https://localhost:44446/signup";
		Assert.assertEquals(url, driver.getCurrentUrl());
		WebElement CancelButton = driver.findElement(By.linkText("Cancelar"));
		CancelButton.click();
		url = "https://localhost:44446/";
		Assert.assertEquals(url, driver.getCurrentUrl());
	}
	
	@Test
	public void LogUserInDatabase() throws InterruptedException {
		WebElement LoginButton = driver.findElement(By.linkText("Login"));
		LoginButton.click();
		Thread.sleep(2000);
		String url = "https://localhost:44446/signup";
		Assert.assertEquals(url, driver.getCurrentUrl());
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("contraseña"));
		WebElement EnviarButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
		email.sendKeys("victor@gmail.com");
		password.sendKeys("hola");
		EnviarButton.click();	
		Thread.sleep(4000);
		url = "https://localhost:44446/questionary";
		Assert.assertEquals(url, driver.getCurrentUrl());
		
	}
	
	@Test
	public void LogFailUserNotInDatabase() throws InterruptedException {
		WebElement LoginButton = driver.findElement(By.linkText("Login"));
		LoginButton.click();
		Thread.sleep(2000);
		String url = "https://localhost:44446/signup";
		Assert.assertEquals(url, driver.getCurrentUrl());
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("contraseña"));
		email.sendKeys("victor2@gmail.com");
		password.sendKeys("hola");
		WebElement EnviarButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
		EnviarButton.click();		
		Assert.assertEquals(url, driver.getCurrentUrl());
	}
	
	
	@Test
	public void LogFailUserUndefined() throws InterruptedException {
		WebElement LoginButton = driver.findElement(By.linkText("Login"));
		LoginButton.click();
		Thread.sleep(2000);
		String url = "https://localhost:44446/signup";
		Assert.assertEquals(url, driver.getCurrentUrl());
		WebElement EnviarButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
		EnviarButton.click();		
		Assert.assertEquals(url, driver.getCurrentUrl());
	}


}
