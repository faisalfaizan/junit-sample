package my.example.junit;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransformationServiceTest {

	TransformationService transformationService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		transformationService = new TransformationService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTransform() {
		String str = "100";
		int value = transformationService.transform(str);
		assertSame(100, value);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testTransformDataError() {
		transformationService.transform("182k");
	}
	
	@Test
	public void testTransformDateToyyyyddMM() {
		Calendar cal = GregorianCalendar.getInstance();
		Date date = cal.getTime();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		String actualValue = transformationService.transform(date);
		String expectedValue = ""+ year + "-" + day + "-" + month; //yyyy-dd-MM
	    assertEquals("Date same", expectedValue, actualValue);
	}

}
