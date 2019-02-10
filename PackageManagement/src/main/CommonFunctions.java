package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Assert;

import constants.GlobalData;

public class CommonFunctions {

	/**
	 * @method executeCommand: will take the first line, and execute the command DEPEND, INSTALL, REMOVE, LIST and END accordingly
	 * @param command
	 * @author Mirtunjay Prasad
	 * @date 9Feb2019
	 */
	public static void executeCommand(String command) {
		try{
			//Asserting the lengh of command is less then 80 characters
			Assert.assertTrue("Length of Command is more then 80 Characters", isCommandLenghtLessThen80(command));
			
			String[] arrSplit = command.split(" ");
			
			//Assert the command name is in upper character
			Assert.assertTrue("Command name is not in UPPER CASE :: " + arrSplit[0], isCommandNameInUpperCase(arrSplit[0]));

			//Performing DEPEND, INSTALL, REMOVE, LIST and END operation
			switch(arrSplit[0]){
			
			case "DEPEND":
				System.out.println(command);
				performDependOperation(command);
				break;

			case "INSTALL":
				System.out.println(command);
				performInstallOperation(command);
				break;

			case "REMOVE":
				System.out.println(command);
				performRemoveOperation(command);
				break;

			case "LIST":
				System.out.println(command);
				performListOperation();
				break;

			case "END":
				System.out.println(command);
				System.exit(0);
				break;

				default:
					System.err.println("Class:: CommonFunctions || Method:: executeCommand || Message:: CommandName is invalid => " + arrSplit[0]);
					Assert.fail("Class:: CommonFunctions || Method:: executeCommand || Message:: CommandName is invalid => " + arrSplit[0]);;
			}
		}catch(Exception e){
			System.err.println("Class:: CommonFunctions || Method:: executeCommand || Message:: Error when executing command :: " +  command);
			e.printStackTrace();
			Assert.fail("Class:: CommonFunctions || Method:: executeCommand || Message:: Error when executing command :: " +  command);
		}
	}

	/**
	 * @method performRemoveOperation: will remove item from the package
	 * @param command
	 * @author Mirtunjay Prasad
	 * @date 9Feb2019
	 */
	private static void performRemoveOperation(String command) {
		try{
			List<List<String>> packageDependency = GlobalData.listPackageDependencies;
			
			String itemRemove = command.split(" ")[1];
			boolean isTrue = true;
			
			Iterator<List<String>> itr = packageDependency.iterator();
			
			while(itr.hasNext()){
				List<String> lst = itr.next();
				
				if(lst.contains(itemRemove)){
					if(itemRemove.length() == 1){
						break;
					}
					for(int i=1; i<lst.size(); i++){
						if(lst.get(i).equals(itemRemove))
						{
							isTrue = false;
							break;
						}
					}
				}
				if(!isTrue){
					break;
				}
			}//End of while loop
			if(isTrue){
				System.out.println(itemRemove + " successfully removed");
				GlobalData.setPackagesInstalled.remove(itemRemove);
			}
			else{
				System.out.println(itemRemove + " is still needed");
			}
		}catch(Exception e){
			System.err.println("Class:: CommonFunctions || Method:: performRemoveOperation || Message:: Error when performing Remove Operation for command :: " + command);
			Assert.fail("Class:: CommonFunctions || Method:: performRemoveOperation || Message:: Error when performing Remove Operation for command :: " + command);
		}
		
	}

	/**
	 * @method performInstallOperation: will install item and its dependency
	 * @param command
	 * @author Mirtunjay Prasad
	 * @date 9Feb2019
	 */
	private static void performInstallOperation(String command) {
		try{
			String[] arrString = command.split(" ");
			String packageItem = arrString[1];
			
			Iterator<List<String>> itr = GlobalData.listPackageDependencies.iterator();
			while(itr.hasNext()){
				List<String> lstPackages = itr.next();
				
				if(lstPackages.contains(packageItem)){
					Iterator<String> subItr = lstPackages.iterator();
					boolean isTrue = false;
					while(subItr.hasNext()){
						String item = subItr.next(); 
						if(isTrue){
							GlobalData.setDependeciesToBeInstalled.add(item);
						}
						if(item.equals(packageItem)){
							isTrue = true;
						}
					}//End of inner while loop
				}//End of if loop
				
			}//End of outer while loop
						
			Iterator<String> setItr = GlobalData.setDependeciesToBeInstalled.iterator();
			
			while(setItr.hasNext()){
				String item = setItr.next();
				if(!GlobalData.setPackagesInstalled.contains(item)){
					System.out.println(item + " successfully installed");
					GlobalData.setPackagesInstalled.add(item);
				}
			}//End of while loop
			if(GlobalData.setPackagesInstalled.contains(packageItem)){
				System.out.println(packageItem + " is already Installed");
			}
			else{
				System.out.println(packageItem + " successfully installed");
				GlobalData.setPackagesInstalled.add(packageItem);				
			}
			
			
		}catch(Exception e){
			System.err.println("Class:: CommonFunctions || Method:: performInstallOperation || Message:: Error when performing Install Operation for command :: " + command);
			Assert.fail("Class:: CommonFunctions || Method:: performInstallOperation || Message:: Error when performing Install Operation for command :: " + command);
		}
		
	}

	/**
	 * @method performListOperation: will list the items in the system package
	 * @author Mirtunjay Prasad
	 * @date 9Feb2019
	 */
	private static void performListOperation() {
		TreeSet<String> tsetPackages = (TreeSet<String>) GlobalData.setPackagesInstalled;
		Iterator<String> itr = tsetPackages.iterator();
		if(!itr.hasNext()){
			System.out.println("No Package Is Installed");			
		}
		while(itr.hasNext()){
			String packageInstall = itr.next();
				System.out.println(packageInstall);				
		}
	}

	/**
	 * @method performDependOperation : will have the item dependencies added to the system package
	 * @param command
	 * @author Mirtunjay Prasad
	 * @date 9Feb2019
	 */
	private static void performDependOperation(String command) {
		try{
			List<String> lstDependencies = new LinkedList<String>();
			String arrString[] = command.split(" ");
			for(int i = 1; i<arrString.length; i++){
				lstDependencies.add(arrString[i]);
			}
			GlobalData.listPackageDependencies.add(lstDependencies);
		}catch(Exception e){
			System.err.println("Class:: CommonFunctions || Method:: performDependOperation || Message:: Error when performing DEPEND Operation for command :: " + command);
			Assert.fail("Class:: CommonFunctions || Method:: performDependOperation || Message:: Error when performing DEPEND Operation for command :: " + command);
		}
		
	}

	/**
	 * @method isCommandNameInUpperCase: will check if the command name is in upper case then return true else return false
	 * @param commandName
	 * @return true/false
	 * @author Mirtunjay Prasad
	 * @date 9Feb2019
	 */
	private static boolean isCommandNameInUpperCase(String commandName) {
		String commandNameUpperCase = commandName.toUpperCase();	
		if(commandNameUpperCase.equals(commandName)){
			return true;
		}
		else 
			return false;
	}

	/**
	 * @method isCommandLenghtLessThen80 : will check if the command length is less then 80 character then return true else return false 
	 * @param command
	 * @return true/false
	 * @author Mirtunjay Prasad
	 * @date 9Feb2019
	 */
	private static boolean isCommandLenghtLessThen80(String command) {
		int len = command.length();
		if(len > 80){
			return false;
		}
		else 
			return true;
	}
}
