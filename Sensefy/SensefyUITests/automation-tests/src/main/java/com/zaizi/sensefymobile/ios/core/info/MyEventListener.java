package com.zaizi.sensefymobile.ios.core.info;
//package com.zaizi.automation.grmt.core.info;
//
//import java.awt.AWTException;
//import java.awt.Rectangle;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Calendar;
//
//import javax.imageio.ImageIO;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.events.WebDriverEventListener;
//
//import com.zaizi.automation.grmt.core.elements.Element;
//
//public class MyEventListener implements WebDriverEventListener {
//    // All the methods of the WebDriverEventListener need to
//    // be implemented here. You can leave most of them blank.
//    // For example...
//    public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
//        // does nothing
//    }
//
//    // ...
//
//    public void onException(Throwable arg0, WebDriver arg1) {
////       System.out.println("Cause Exception start :" + arg0.getCause() + " End of exception");
////       System.out.println("mesg Exception start :" + arg0.getMessage() + " End of exception");
////       System.out.println("local mesg Exception start :" + arg0.getLocalizedMessage() + " End of exception");
//    	String arg ;
//    	arg = arg0.getCause().toString();
////    	 System.out.println(arg);
//       if(arg.contains("org.openqa.selenium.NoSuchWindowException") || arg.contains("org.openqa.selenium.NoSuchElementException"))
//       {
////    	   System.out.println("No exception");
//       }
//       else
//    	   {String filename = generateRandomFilename(arg0);
//    	   System.out.println("Exception Found");
//    	   System.out.println(arg);
//        createScreenCaptureJPEG(filename,arg1);
//    	   }
//    }
//
//	private String generateRandomFilename(Throwable arg0) {
//		// TODO Auto-generated method stub
//		 Calendar c = Calendar.getInstance();
//	        String filename = arg0.getMessage();
//	        int i = filename.indexOf('\n');
//	        filename = filename.substring(0, i).replaceAll("\\s", "_").replaceAll(":", "") + ".jpg";
//	        filename = "" + c.get(Calendar.YEAR) + 
//	            "-" + c.get(Calendar.MONTH) + 
//	            "-" + c.get(Calendar.DAY_OF_MONTH) +
//	            "-" + c.get(Calendar.HOUR_OF_DAY) +
//	            "-" + c.get(Calendar.MINUTE) +
//	            "-" + c.get(Calendar.SECOND) +
//	            "-" + filename;
//	        return filename;
//	}
//	private void createScreenCaptureJPEG(String filename,WebDriver driver) {
//		  try {
////		   BufferedImage img = getScreenAsBufferedImage();
////		   File output = new File(filename);
////		   ImageIO.write(img, "jpg", output);
//			  Element.onTestfail(driver, filename);
//		  } catch (Exception e) {
//		   e.printStackTrace();
//		  }
//		 }
//		 
////		 private BufferedImage getScreenAsBufferedImage() {
////		  BufferedImage img = null;
////		  try {
////		   Robot r;
////		   r = new Robot();
////		   Toolkit t = Toolkit.getDefaultToolkit();
////		   Rectangle rect = new Rectangle(t.getScreenSize());
////		   img = r.createScreenCapture(rect);
////		  } catch (AWTException e) {
////		   e.printStackTrace();
////		  }
////		  return img;
////		 }
//
//	/*@Override
//	public void afterClickOn(WebElement arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void afterNavigateBack(WebDriver arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void afterNavigateForward(WebDriver arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void afterNavigateTo(String arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void afterScript(String arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeNavigateBack(WebDriver arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeNavigateForward(WebDriver arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeNavigateTo(String arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeScript(String arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}*/
//}