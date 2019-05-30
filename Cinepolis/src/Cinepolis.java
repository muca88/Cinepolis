

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import library.ExcelDataConfig;
import library.Utility;

public class Cinepolis {

	WebDriver driver;
	ExcelDataConfig excel;
	String pagina, nombreTarjetahabiente, numeroTarjeta, codigoSeguridad, correoElectronico
	,telefono, tarjetaClub, mensajeUno;
	Date date;
	DateFormat hourdateFormat;
	ATUTestRecorder grabar;
	JavascriptExecutor jse;
	WebDriverWait wait;
	WebElement boton, mensajeDos;
	
	@BeforeTest
	public void navegador() throws Exception 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		excel = new ExcelDataConfig("C:\\Users\\Qualtop\\Documents\\ExcelData\\TestData.xlsx");
		
		pagina = excel.getData(1, 1, 1);
		driver.get(pagina);
		
		date = new Date();
		hourdateFormat = new SimpleDateFormat("HHmmss_ddMMyyyy");
		grabar= new ATUTestRecorder("Video","cinepolis" + hourdateFormat.format(date), false);
		jse = (JavascriptExecutor) driver;
		grabar.start();
	}
	
	@Test(priority=0)
	public void inicio() 
	{
		//Test 1
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//select[@id='cmbCiudades']//option[contains(text(),'CDMX Sur')]")).click();
		driver.findElement(By.xpath("//option[contains(text(),'Cinépolis Oasis Coyoacán')]")).click();
	}
	
	@Test(priority=1)
	public void pelicula() 
	{
		//Test 2
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Utility.captureScreenshot(driver, "1_Evidencia_" + hourdateFormat.format(date));
		
		driver.findElement(By.xpath("//div[@id='cinepolis-oasis-coyoacan-31182-dulce-familia']//time[1]")).click();	
	}
	
	@Test(priority=2)
	public void seleccionaHorario() 
	{	
		wait = new WebDriverWait(driver, 20);
		
		jse.executeScript("window.scrollBy(0,300)");
		
		boton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/form[1]/div[2]/section[1]/article[1]/font[1]/div[1]/fieldset[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[3]/td[3]/div[1]/button[2]")));
		boton.click();
		
		Utility.captureScreenshot(driver, "2_Evidencia_" + hourdateFormat.format(date));
		
		jse.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("//input[@id='ibtnOrderTickets']")).click();
		
		Utility.captureScreenshot(driver, "3_Evidencia_" + hourdateFormat.format(date));
	}
	
	@Test(priority=3)
	public void escogeLugar() 
	{	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		jse.executeScript("window.scrollBy(0,400)");
		
		Utility.captureScreenshot(driver, "4_Evidencia_" + hourdateFormat.format(date));
		
		driver.findElement(By.xpath("//input[@id='ibtnNext']")).click();
	}
	
	@Test(priority=4)
	public void iniciaSesion() 
	{	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		jse.executeScript("window.scrollBy(0,400)");
		
		Utility.captureScreenshot(driver, "5_Evidencia_" + hourdateFormat.format(date));
		
		driver.findElement(By.xpath("//input[@id='btnContinuar']")).click();
	}
	
	@Test(priority=5)
	public void confirmaCompra() 
	{	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		jse.executeScript("window.scrollBy(0,600)");
		
		nombreTarjetahabiente = excel.getData(1, 4, 1);
		driver.findElement(By.xpath("//input[@id='txtCardName']")).sendKeys(nombreTarjetahabiente);
		
		numeroTarjeta = excel.getData(1, 5, 1);
		driver.findElement(By.xpath("//input[@id='txtCardNumber']")).sendKeys(numeroTarjeta);
		
		codigoSeguridad = excel.getData(1, 6, 1);
		driver.findElement(By.xpath("//input[@id='txtCardCVCNumber']")).sendKeys(codigoSeguridad);
		
		driver.findElement(By.xpath("//select[@id='dropCardExpiryMonth']//option[contains(text(),'01')]")).click();
		driver.findElement(By.xpath("//option[contains(text(),'2021')]")).click();
		
		jse.executeScript("window.scrollBy(0,300)");
		
		driver.findElement(By.xpath("//option[contains(text(),'Mastercard')]")).click();
		
		correoElectronico = excel.getData(1, 7, 1);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(correoElectronico);
		
		driver.findElement(By.xpath("//option[contains(text(),'Telcel')]")).click();
		
		telefono = excel.getData(1, 8, 1);
		driver.findElement(By.xpath("//input[@id='txtCustPhone']")).sendKeys(telefono);
		
		tarjetaClub = excel.getData(1, 9, 1);
		driver.findElement(By.xpath("//input[@id='txtLoyaltyCardNumber']")).sendKeys(tarjetaClub);
		
		Utility.captureScreenshot(driver, "6_Evidencia_" + hourdateFormat.format(date));
		
		jse.executeScript("window.scrollBy(0,200)");
		
		driver.findElement(By.xpath("//input[@id='chkTerms']")).click();
		
		Utility.captureScreenshot(driver, "7_Evidencia_" + hourdateFormat.format(date));
		
		driver.findElement(By.xpath("//input[@id='ibtnPayNow2']")).click();
		
	}
	
	@Test(priority=6)
	public void mensaje() 
	{	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		jse.executeScript("window.scrollBy(0,400)");
		
		Utility.captureScreenshot(driver, "8_Evidencia_" + hourdateFormat.format(date));
		
		mensajeUno="El número de tu tarjeta Club Cinépolis o la dirección de correo electrónico que ingresaste es incorrecta. Por favor vuelve a intentarlo.";
		mensajeDos = driver.findElement(By.xpath("//p[contains(text(),'El número de tu tarjeta Club Cinépolis o la direcc')]"));
		
		Assert.assertEquals(mensajeUno, mensajeDos.getText());
				
	}
	
	@AfterTest(enabled=true)
	public void cerrarTest() throws ATUTestRecorderException 
	{
		grabar.stop();
		driver.quit();
	}
	
	
	
	
}
