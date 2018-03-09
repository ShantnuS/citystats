package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RIKGetter {

	public static String getRegion() throws IOException{
		return Files.readAllLines(Paths.get("res//RIK.rik")).get(0);
	}
	
	public static String getId() throws IOException {
		return Files.readAllLines(Paths.get("res//RIK.rik")).get(1);
	}
	
	public static String getKey() throws IOException {
		return Files.readAllLines(Paths.get("res//RIK.rik")).get(2);
	}
	
}
