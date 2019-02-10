package constants;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GlobalData {

	public static String fileLocation = System.getProperty("user.dir") + "//src//testdata//";

	public static List<String> inputString  = new LinkedList<String>();
	
	public static List<List<String>> listPackageDependencies = new LinkedList<List<String>>();
	
	public static Set<String> setPackagesInstalled = new TreeSet<String>();
	
	public static Set<String> setDependeciesToBeInstalled = new LinkedHashSet<String>();
}
