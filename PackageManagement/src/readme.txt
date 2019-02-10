Please follow the below steps to run the test
i. Configure build path for junit.jar present in folder /PackageManagement/src/jars
ii. Test Data file present in location /PackageManagement/src/testdata
iii. In /PackageManagement/src/main/Main.java class enable string filename to run specific test
	- String filename = "input.txt";
	- String filename = "CommandInLowerCase.txt";
	- String filename = "CommandLengthMoreThen80Char.txt";
iv. Console will output the result of execution

Code Analysis:
- readFile method will read the file and return each line of string as list<String>
- For Each line the command name will be split 
- Respective action "DEPEND, INSTALL, REMOVE, LIST and END" action will be performed

Data Structure:
- public static List<List<String>> listPackageDependencies = new LinkedList<List<String>>();
	Will have the DEPEND command details with package items and its dependencies
- public static Set<String> setPackagesInstalled = new TreeSet<String>();
	Will contain the packages that has been currently installed
- public static Set<String> setDependeciesToBeInstalled = new LinkedHashSet<String>();
 	Will contain the dependencies to be installed before the INSTALL item operation
 	
For any query/clarification/suggesstion please contact mirtunjay.prasad@gmail.com	