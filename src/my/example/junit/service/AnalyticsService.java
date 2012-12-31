package my.example.junit.service;

import java.util.List;

public interface AnalyticsService<E> {
	
	public void save(Object obj);
	public void save(List<E> list);
	public void update(Object obj);
	public void update(List<E> list);
	
}
