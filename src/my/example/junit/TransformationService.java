package my.example.junit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TransformationService {

	public int transform(String str){
		return Integer.parseInt(str);
	}
	
	public String transform(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat();
		String pattern = "yyyy-dd-MM";
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}
	
	public List<List<Object>> makeChunks(List<Object> list, int partitionSize){
		List<List<Object>> resultList = new LinkedList<List<Object>>();
		int size = list.size();
		
		for(int i = 0; i < size; i += partitionSize){
			int lastIndex = i + partitionSize;
			if(lastIndex > size)
				lastIndex = size;
			resultList.add(list.subList(i, lastIndex));
		}
		return resultList;
	}
}

