package my.example.junit;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
