package com.onemount.dungtran.common;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Assert {

	public static FileWriter fileWriter;
	
	public static void assertContains(String actualText, String expectText, String message) {
		try {
			if (actualText.contains(expectText)) {
				fileWriter.write(message + "PASSED\n");
			} else {
				fileWriter.write(message + "FAILED \n\t Don't go to the expected page.\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void assertEquals(String actualText, String expectText, String message) {
		try {
			if (actualText.equals(expectText)) {
				fileWriter.write(message + "PASSED\n");
			} else {
				fileWriter.write(message + "FAILED \n\t Expect is \"" + expectText + "\" but actual is \"" + actualText
						+ "\"\n");
			}
		} catch (IOException e) {
			System.out.println("An error occurred in " + message);
			e.printStackTrace();
		}
	}

	public static void openFileWriter(String fileName, boolean append) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ssmmHHddMMyyyy");  
		   LocalDateTime now = LocalDateTime.now();  
		try {
			fileWriter = new FileWriter("final_result_"+ fileName + "_test_" + dtf.format(now) + ".txt", append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeFileWriter() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
