package project5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** This class is our main program
 *The program expects 1 command line arguments and will check
 *if it is a valid file and exists
 *If not the program will terminate
 *In this class we build our mountain/BST based off the file we are given 
 *
* @author Diana Yepes! (:<
*/



public class BSTMountainAdventure {

	/**
	 * The main() method of this program. 
	 * @param args: the file inputed as the first command line argument
	 *Here we print out all output and take care of possible file errors
	 */	
	public static void main(String[] args) {
		//Check that there is a command line argument
		//If not, we end the program!
		if (args.length == 0 ) {   			
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1); 				
		}
 		//Create new scanner and file from command line
		File f = new File(args[0]);
		Scanner scanny=null;
		//make our tree
		BSTMountain theeMountain= new BSTMountain();
		
		//verify that command line argument contains a name of an existing file
 		if (!f.exists()){ 			
			System.err.println("Error: the file "+f.getAbsolutePath()+" does not exist.\n");
			System.exit(1);				
 		}
 		
 		try {
			scanny = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.err.println("Error file " + f.getAbsolutePath()+"not found");
			System.exit(1);
		}
 		//Now we Build our tree based on input file
 		while(scanny.hasNextLine()) {
 			String line=scanny.nextLine();
 			//We need to get the labels separately from obstacles and supplies
 			String str[] =line.split(" ");
 			List<String> list = Arrays.asList(str);
 			//Create new RestStop using label
 			RestStop labels=new RestStop(list.get(0));
 			//Now we need to add the supplies and obstacles to the RestStop
 			for(int i=1;i<list.size();i++) {
 				//We check that its in the correct order with supplies coming before obstacles
 				if((labels.getObstacles().isEmpty())){
 					if(list.get(i).equalsIgnoreCase("food")||list.get(i).equalsIgnoreCase("axe")||list.get(i).equalsIgnoreCase("raft")) {
 						labels.getSupplies().add(list.get(i).toLowerCase());
 					}
 					else if(list.get(i).equalsIgnoreCase("river")){
 						labels.getObstacles().add("river");
 					}
 					else if(list.get(i+1).equalsIgnoreCase("tree")) {
							labels.getObstacles().add("fallen tree");
						}
						else {
							continue;
						}
 				}
 				//Otherwise obstacles was first so we ignore invalid inputs and supplies that might come after
 				else {
 					if(list.get(i).equalsIgnoreCase("river")) {
 						labels.getObstacles().add("river");
 					}
 					if(list.get(i).equalsIgnoreCase("fallen")) {
 						if(list.get(i+1).equalsIgnoreCase("tree")) {
 							labels.getObstacles().add("fallen tree");
 						}
 						else {
 							continue;
 						}
 					}
 				}
 			}	
 			//add the RestStop to our mountain/BST
 			theeMountain.add(labels);
 		}
 		//Create our Hiker
 		Hiker hiker1=new Hiker();
 		//Call his traversal method and print out paths only if they exist
 		if((hiker1.hikerSurvivalPaths(hiker1.hikerPaths(theeMountain)))!="") {
 			System.out.println(hiker1.hikerSurvivalPaths(hiker1.hikerPaths(theeMountain)));
 		}	

	}

}

