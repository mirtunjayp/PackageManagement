package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;

import constants.GlobalData;

public class FileUtils {

	/**
	 * @method readFile: will read a text file and return each line as list of string
	 * @param filename
	 * @return : list<String>
	 * @author Mirtunjay
	 * @Date 9Feb2019
	 */
	public static List<String> readFile(String filename){
		List<String> lst = new LinkedList<String>();
		try{
			filename = GlobalData.fileLocation + filename;
			File file = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String str;
			
			while((str = br.readLine()) != null){
				lst.add(str);
			}
			
		}catch(Exception e){
			System.err.println("Class:: FileUtils || Method:: readFile || Message: Error when reading file :: " + filename);
			Assert.fail("Class:: FileUtils || Method:: readFile || Message: Error when reading file :: " + filename);
		}
		return lst;
	}
}
