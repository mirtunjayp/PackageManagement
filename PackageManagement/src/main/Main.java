package main;

import constants.GlobalData;
import util.FileUtils;

public class Main {

	public static void main(String[] args) {
		//Enable one of the three file name to get the details in console output
		String filename = "input.txt";
//		String filename = "CommandInLowerCase.txt";
//		String filename = "CommandLengthMoreThen80Char.txt";
		
		//Reading the content from the file and storing in global data 
		GlobalData.inputString = FileUtils.readFile(filename);
		
		int numOfCommand = GlobalData.inputString.size();

		//Run each line of string, and get the command from each line and perform respective command accordingly
		for(int i=0; i< numOfCommand; i++){			
			CommonFunctions.executeCommand(GlobalData.inputString.get(i));
		}
		
	}

}
