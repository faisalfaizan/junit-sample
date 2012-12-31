package my.example.junit.dao;

import java.util.List;

public interface AnalyticsDao<E> {
	public void save(List<E> list);
	public void save(Object obj);
	public void update(List<E> list);
	public void update(Object obj);
}
