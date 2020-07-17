package resdbustTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import excelSheetData.WriteExcel;
public class ResdbusTest {

	public WebDriver driver;
	  //public WebElement element;
	WriteExcel read=new WriteExcel("D:\\FleetainWorkSpace\\SimpleProgram\\src\\excelFile\\redbus"+setFilename()+".xlsx");
	    //ENTER DATE HERE
	 Calendar now = Calendar.getInstance();
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    String date = now.get(Calendar.DATE)+"-"+get_Month()+" 2019"; 
		String splitter[] = date.split("-");
		String month_year = splitter[1];
		String day = splitter[0];	
		 public String get_Month(){
			   Date date = new Date();
			   SimpleDateFormat formatter = new SimpleDateFormat("MMM");
			   return formatter.format(date);          
			 }
		String routes[][]= {
				
				{"Kolhapur(Maharashtra) (All Locations)","Pune (All Locations)"},
				{"Mumbai (All Locations)","Kolhapur(Maharashtra) (All Locations)"},	
		        {"Kolhapur(Maharashtra) (All Locations)","Aurangabad (All Locations)"},
			    {"Kolhapur(Maharashtra) (All Locations)","Hyderabad (All Locations)"},
				{"Kolhapur(Maharashtra) (All Locations)","Nashik (All Locations)"},
				{"Kolhapur(Maharashtra) (All Locations)","Mumbai (All Locations)"},
				{"Kolhapur(Maharashtra) (All Locations)","Bangalore (All Locations)"},
             	{"Sangli","Pune (All Locations)"},
				{"Pune (All Locations)","Hyderabad(All Locations)"},
				{"Nashik (All Locations)","Kolhapur(Maharashtra) (All Locations)"},
				{"Hyderabad (All Locations)","Kolhapur(Maharashtra) (All Locations)"},
				{"Hyderabad (All Locations)","Pune (All Locations)"},
				{"Bangalore (All Locations)","Kolhapur(Maharashtra) (All Locations)"},
				{"Aurangabad (All Locations)","Kolhapur(Maharashtra) (All Locations)"},
				{"Pune (All Locations)","Sangli"},
				{"Pune (All Locations)","Kolhapur(Maharashtra) (All Locations)"},
	    
		};
			public  ResdbusTest() {
			read.addSheet("Routs");
			read.addColumn("Routs", "DATE");
			read.addColumn("Routs", "TIME");
			read.addColumn("Routs", "JOURNEYDATE");
			read.addColumn("Routs", "STARTPOINT");
			read.addColumn("Routs", "ENDPOINT");
			read.addColumn("Routs", "TRAVELNAME");
			read.addColumn("Routs", "PRISE");
			read.addColumn("Routs", "SEATSAVALABLE");
			read.addColumn("Routs", "TOTALTIME");
			read.addColumn("Routs", "STARTINGTIME");
			read.addColumn("Routs", "ENDTIME");
			read.addColumn("Routs", "BUSTYPE");
			read.addColumn("Routs", "RATEQUANTITY");
			read.addColumn("Routs", "RATING");
			read.addColumn("Routs", "FIXTIME");
		}

		public void selectDate(String monthyear, String Selectday) throws InterruptedException
		{		
			List<WebElement> elements = driver.findElements(By.xpath("//div[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[2]"));
					
			for (int i=0; i<elements.size();i++)
			{
				System.out.println(elements.get(i).getText());
				//Selecting the month
				if(elements.get(i).getText().equals(monthyear))
				{				
					//Selecting the date				
					List<WebElement> days = driver.findElements(By.xpath(".//*[@id='rb-calendar_onward_cal']/table/tbody/tr/td"));
					
					for (WebElement d:days)
					{					
						//System.out.println(d.getText());
						if(d.getText().equals(Selectday))
						{
							d.click();
							Thread.sleep(5000);
							return;
						}
					}									
				}						
			}
			driver.findElement(By.xpath("//*[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[3]/button")).click();
			selectDate(monthyear,Selectday);	
		}
		
		public String getCurrentDate()
		{
		    return new    SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
		}
		public String getCurrentTime()
		{
		   return new   SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());	
		}
		
		public static final String setFixTime() {
			return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		}
		
		public static final String setFilename() {
			return new SimpleDateFormat("dd-HH-mm").format(Calendar.getInstance().getTime());
		}
		
		String fixTime=setFixTime();
//new root Enter method
		@Test
		public void TravelDetails() throws InterruptedException{
			
 		int temp=2;
			
			int ArrayLength = this.routes.length; 
			System.out.println(ArrayLength);
			
			for(int k=0;k < ArrayLength;k++)
			{
			        	
				    ChromeOptions options = new ChromeOptions();
				    options.addArguments("--disable-notifications");
				    System.setProperty("webdriver.chrome.driver","D:\\FleetainWorkSpace\\SimpleProgram\\chromedriver.exe");
					driver=new ChromeDriver(options);
					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					driver.manage().deleteAllCookies();
					driver.get("https://www.redbus.in");
					driver.findElement(By.id("src")).click();
					driver.findElement(By.id("src")).sendKeys(this.routes[k][0]);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id='search']/div/div[1]/div/ul/li[1]")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("dest")).click();
					driver.findElement(By.id("dest")).sendKeys(this.routes[k][1]);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id='search']/div/div[2]/div/ul/li[1]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath(".//*[@id='search']/div/div[3]/div/label")).click();
					Thread.sleep(2000);
					System.out.println(month_year);
					System.out.println(day);
					selectDate(month_year,day);		
					Thread.sleep(2000);
					driver.findElement(By.xpath(".//*[@id='search_btn']")).click();
					Thread.sleep(2000);
					
					
					int nextDayClick=0;		
					
         for(int nextDay=0;nextDay<=3;nextDay++) {
			
				
			List<WebElement> detail= driver.findElements(By.xpath("//div[@class='travels lh-24 f-bold d-color']"));
			List<WebElement> prise=driver.findElements(By.xpath("//div[@class='fare d-block']"));
			List<WebElement> seats =driver.findElements(By.xpath("//div[@class='seat-left m-top-16']"));
			List<WebElement> rating=driver.findElements(By.xpath("//div[@class='rating-sec lh-24']"));
			List<WebElement> startTime=driver.findElements(By.xpath("//div[@class='dp-time f-19 d-color f-bold']"));
			List<WebElement> endTime=driver.findElements(By.xpath("//div[@class='bp-time f-19 d-color disp-Inline']"));
			List<WebElement> totalTime=driver.findElements(By.xpath("//div[@class='dur l-color lh-24']"));
			List<WebElement> rateQuentity=driver.findElements(By.xpath("//div[@class='no-ppl m-top-16 l-color']"));
			List<WebElement> busType=driver.findElements(By.xpath("//div[@class='bus-type f-12 m-top-16 l-color']"));
			String startPoint=driver.findElement(By.xpath("//span[@class='src']")).getText();
			String endPoint=driver.findElement(By.xpath("//span[@class='dst']")).getText();
			//String journeyDate=driver.findElement(By.xpath("//span[@class='dateDispOnw']")).getText();
			String journeyDate = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",driver.findElement(By.xpath("//input[@id='searchDat']")));
			List<WebElement> totalTravels=driver.findElements(By.xpath("//li[@class='row-sec clearfix']"));
			
			   
			 try { 
	       
	       for(int i=0;i<totalTravels.size();i++){
	    	          	   		   
	     System.out.println(startPoint+"=>"+endPoint+"=>"+detail.get(i).getText()+"=>"+prise.get(i).getText());
	    /* +"=>"+seats.get(i).getText()+"=>"+startTime.get(i).getText()+"=>"+busType.get(i).getText());*/

	   
	    	        String travel=detail.get(i).getText();
			        read.setCellData("Routs", "TRAVELNAME", temp, travel);
			        String fair=prise.get(i).getText();
		            read.setCellData("Routs", "PRISE", temp, fair);
			        String seatAvl=seats.get(i).getText();
		            read.setCellData("Routs", "SEATSAVALABLE", temp, seatAvl);
                    String rate=rating.get(i).getText();
			        read.setCellData("Routs", "RATING", temp, rate);
			        String sTime=startTime.get(i).getText();
			        read.setCellData("Routs", "STARTINGTIME",temp, sTime);
			        String eTime=endTime.get(i).getText();
			        read.setCellData("Routs", "ENDTIME", temp, eTime);
			        String tTime=totalTime.get(i).getText();
			        read.setCellData("Routs", "TOTALTIME", temp, tTime);
			        String buType=busType.get(i).getText();
			        read.setCellData("Routs", "BUSTYPE", temp, buType);
			        read.setCellData("Routs", "STARTPOINT", temp, startPoint);
			        read.setCellData("Routs", "ENDPOINT", temp, endPoint );
			        read.setCellData("Routs", "JOURNEYDATE", temp, journeyDate);
			        read.setCellData("Routs", "DATE", temp, getCurrentDate());
			        read.setCellData("Routs", "TIME", temp, getCurrentTime());
			        read.setCellData("Routs", "FIXTIME", temp, fixTime);
			         try{
			        String rateQue=rateQuentity.get(i).getText();
			        read.setCellData("Routs", "RATEQUANTITY", temp, rateQue);
			         
			         }catch(IndexOutOfBoundsException e) {
							e.printStackTrace();	
						}
	    	   
	    	     temp++;    
	       } // for loop end 
	       }catch(IndexOutOfBoundsException e) {
				e.printStackTrace();
			
			}// catch block
			 Thread.sleep(2000);
			 driver.findElement(By.className("next")).click();
			}// click to next day routs page 
			
         nextDayClick++;
	       
	       }  // array routes for loop end 	
	  }// Travel Detail Method 
	/*public static void main(String[] args) throws InterruptedException {
		Daily2HRProg data=new Daily2HRProg();
		data.TravelDetails();	
	}*/
	
	
	
}
	
	
	
	
	
