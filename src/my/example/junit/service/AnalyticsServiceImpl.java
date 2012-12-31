package my.example.junit.service;

import java.util.List;

import my.example.junit.dao.AnalyticsDao;
import my.example.junit.domain.Analytics;

public class AnalyticsServiceImpl<T> implements AnalyticsService<Analytics> {

	AnalyticsDao<Analytics> analyticsDao;
	
	@Override
	public void save(Object obj) {
		analyticsDao.save(obj);
	}

	@Override
	public void save(List<Analytics> list) {
		analyticsDao.save(list);
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void update(List<Analytics> list) {
		// TODO Auto-generated method stub

	}

	public AnalyticsServiceImpl(AnalyticsDao<Analytics> analyticsDao){
		this.analyticsDao = analyticsDao;
	}
	
	public void setAnalyticsDao(AnalyticsDao<Analytics> analyticsDao) {
		this.analyticsDao = analyticsDao;
	}
}
