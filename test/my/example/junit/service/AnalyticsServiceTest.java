package my.example.junit.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import my.example.junit.dao.AnalyticsDao;
import my.example.junit.domain.Analytics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AnalyticsServiceTest {
	
	AnalyticsService<Analytics> analyticsService;
	AnalyticsDao<Analytics> mockAnalyticsDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		mockAnalyticsDao = mock(AnalyticsDao.class);
		analyticsService = new AnalyticsServiceImpl<Analytics>(mockAnalyticsDao);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void saveSingleAnalytics() {
		Analytics analytics = new Analytics();
		analytics.setName("Vodafone");
	
		analyticsService.save(analytics);
		verify(mockAnalyticsDao).save(analytics);
	}
	
	@Test(expected = Throwable.class)
	public void saveSingleAnalyticsThrowsThrowable() {
		Analytics analytics = new Analytics();
		analytics.setName("Vodafone");
	
		doThrow(new Throwable()).when(mockAnalyticsDao).save(analytics);
		analyticsService.save(analytics);
		verify(mockAnalyticsDao).save(analytics);
	}

	@Test
	public void saveListOfAnalytics() {
		Analytics analytics = new Analytics();
		analytics.setName("Vodafone");
		List<Analytics> analyticsList = new ArrayList<Analytics>();
		analyticsList.add(analytics);
		
		analyticsService.save(analyticsList);
		verify(mockAnalyticsDao).save(analyticsList);
	}
	
	@Test(expected = Throwable.class)
	public void saveListOfAnalyticsThrowsThrowable() {
		Analytics analytics = new Analytics();
		analytics.setName("Vodafone");
		List<Analytics> analyticsList = new ArrayList<Analytics>();
		analyticsList.add(analytics);
		
		doThrow(new Throwable()).when(mockAnalyticsDao).save(analyticsList);
		
		analyticsService.save(analyticsList);
		verify(mockAnalyticsDao).save(analyticsList);
	}
	
	@Ignore
	@Test
	public void testUpdateObject() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testUpdateListOfE() {
		fail("Not yet implemented");
	}

}
