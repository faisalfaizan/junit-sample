package my.example.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
	public void transformStringToNumber() {
		String str = "100";
		int value = transformationService.transform(str);
		assertSame(100, value);
	}
	
	@Test(expected = NumberFormatException.class)
	public void transformStringToNumberWithError() {
		transformationService.transform("182k");
	}
	
	@Test
	public void transformDateToStringFormatyyyyddMM() {
		Calendar cal = GregorianCalendar.getInstance();
		Date date = cal.getTime();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		String actualValue = transformationService.transform(date);
		String expectedValue = ""+ year + "-" + day + "-" + month; //yyyy-dd-MM
	    assertEquals("Same Date", expectedValue, actualValue);
	}

	@Test
	public void transformListOfObjectsToSmallerChunks(){
		List<List<Object>> expectedList = null;
		List<Object> originalList = new ArrayList<Object>();
		originalList.add("First");
		originalList.add("Second");
		originalList.add("Third");
		originalList.add("Fourth");
		originalList.add("Fifth");
		
		int partitionSize = 2;
		expectedList = transformationService.makeChunks(originalList, partitionSize);
		
		assertSame("3 chunks made", expectedList.size(), 3);
		assertSame(expectedList.get(0).size(), 2);
		assertSame(expectedList.get(1).size(), 2);
		assertSame(expectedList.get(2).size(), 1);
		
		assertArrayEquals(originalList.subList(0, 2).toArray(), expectedList.get(0).toArray());
	}
	
	@Test
	public void transformEmptyListToSmallerChunks(){
		List<List<Object>> expectedList = null;
		List<Object> originalList = new ArrayList<Object>();
		
		int partitionSize = 2;
		expectedList = transformationService.makeChunks(originalList, partitionSize);
		
		assertSame("Empty", expectedList.size(), 0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=NullPointerException.class)
	public void transformNullToSmallerChunks(){
		List<List<Object>> expectedList = null;
		List<Object> originalList = null;
		
		int partitionSize = 2;
		expectedList = transformationService.makeChunks(originalList, partitionSize);
	}
	
	@Test
	public void transformEvenListToChunksOfOddSize(){
		List<List<Object>> expectedList = null;
		List<Object> originalList = new ArrayList<Object>();
		originalList.add("First");
		originalList.add("Second");
		originalList.add("Third");
		originalList.add("Fourth");
		originalList.add("Fifth");
		originalList.add("Sixth");
		
		int partitionSize = 3;
		expectedList = transformationService.makeChunks(originalList, partitionSize);
		
		assertSame("2 chunks made", expectedList.size(), 2);
		assertSame(expectedList.get(0).size(), 3);
		assertSame(expectedList.get(1).size(), 3);
			
		assertArrayEquals(originalList.subList(0, 3).toArray(), expectedList.get(0).toArray());
	}
	
	@Test
	public void transformListToChunksOfSameSize(){
		List<List<Object>> expectedList = null;
		List<Object> originalList = new ArrayList<Object>();
		originalList.add("First");
		originalList.add("Second");
		originalList.add("Third");
		originalList.add("Fourth");
		originalList.add("Fifth");
		originalList.add("Sixth");
		
		int partitionSize = 6;
		expectedList = transformationService.makeChunks(originalList, partitionSize);
		
		assertSame("Single chunk made", expectedList.size(), 1);
		assertSame(expectedList.get(0).size(), 6);
			
		assertArrayEquals(originalList.subList(0, 6).toArray(), expectedList.get(0).toArray());
	}
}
