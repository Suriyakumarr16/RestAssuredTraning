package DataDriven;

import java.io.IOException;
import java.util.ArrayList;

public class DataDrivenTest {
	public static void main(String[] args) throws IOException {
		
		DataDriven d = new DataDriven();
		
		ArrayList<String> list = d.getData("Appium","TestData");
	System.out.println(list.get(0));
	}
}
