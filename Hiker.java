package project5;
import java.util.ArrayList;

/** This is our Hiker class
 * It represents YOU, the hiker, or really any general hiker going down the mountain
 * The hiker class has the actual traversal methods which finds the survival paths down the mountain
 * It takes care of updating the hiker supplies and finding its way down the BST
 *
* @author Diana Yepes!
*/


public class Hiker {
	
	//private hiker supplies field
	//This arraylist should store the hikers supplies and what he obtains
	private ArrayList<String> hikerSupplies=new ArrayList<String>();
	
	/**
	 *getter method for the hiker supplies field 
	 * @return arraylist of the supplies
	 */
	public ArrayList<String> getHikerSupplies() {
		return hikerSupplies;
	}
	/**
	 *setter method for the RestStop label 
	 * @param new arraylist to be set as hiker supplies
	 */
	public void setHikerSupplies(ArrayList<String> hikerSupplies) {
		this.hikerSupplies = hikerSupplies;
	}
	
	/**
	 * Wrapper method for our recursive traversal method 
	 * it will call the actual recursive method with ddesignated arguments
	 * @param bstTree: the mountain/BST we will we traveling down 
	 * @return a 2D arraylist with possible paths down mountain
	 */
	public ArrayList<ArrayList<RestStop>> hikerPaths (BSTMountain bstTree){
		//create the things we need in order to call the recursive method
		ArrayList<ArrayList<RestStop>> allPaths=new ArrayList<ArrayList<RestStop>>();
		ArrayList<RestStop> eachPath = new ArrayList<RestStop>();
		//Here we call the depth method from the BSTMountain class to get the tree depth
		int theeDepth=bstTree.maxDepth(bstTree.getRoot());
		int nodesL=0;
		//call recursive method 
		return paths(bstTree.getRoot(),allPaths,eachPath,theeDepth,nodesL);
	}	
	
	/**
	 * Actual recursive method which finds possible paths down mountain
	 * This method is only used to discard paths that lead down a cliff
	 * @param node,pathsArr,pathArr,depth,levelNow: Each node we are using to get through the tree, a 2D arraylist which will store all paths,
	 * a 1D arraylist which will store each RestStop in the path, the int of the depth we will use to see node is a cliff or not,
	 * and an int which is updated to compare the depth we are at now to the actual tree depth
	 * @return a 2D arraylist with possible paths down mountain
	 */
	public ArrayList<ArrayList<RestStop>> paths (BSTMountain.BSTNode node,ArrayList<ArrayList<RestStop>> pathsArr,ArrayList<RestStop> pathArr,int depth,int levelNow){
		if (node == null) {
			 return pathsArr;
		}
	    /*add this RestStop to the RestStops arraylist */
		pathArr.add(node.getData());
		//update level we are at
		levelNow=levelNow+1;
	    //check if we are at the bottom of the tree, making sure the level and depth of the mountain are equal
	    if (node.getLeft() == null && node.getRight() == null && levelNow==depth) {
	    	//Now we add the RestStops arraylist to the possible paths arraylist
	    	ArrayList<RestStop> copy=new ArrayList<RestStop>();
	    	copy.addAll(pathArr);
	    	pathsArr.add(copy);
		}
	    else {
	    	/* otherwise try both subtrees through recursion */
	    	if(node.getLeft()!=null) {
		    	paths(node.getLeft(), pathsArr, pathArr,depth,levelNow);
		    	//we remove the RestStops from the RestStop arraylist once the recursion for them ends
		    	pathArr.remove(pathArr.size()-1); 	  	
	    	}
	    	if(node.getRight()!=null) {
	    		paths(node.getRight(), pathsArr, pathArr,depth,levelNow);
	    		//we remove the RestStops from the RestStop arraylist once the recursion for them ends
		    	pathArr.remove(pathArr.size()-1); 	
	    	}
	    }
	    //update our level
	    levelNow=levelNow-1;
	    //return the 2D arraylist
		return pathsArr;
	}

	/**
	 * This method iterates through any paths we found in the recursive method
	 * Here, the hiker should update his supplies as he traverses each edge, and check if he
	 * can get through the obstacles
	 * @param livePaths: the 2D arraylist which stored the possible paths down mountain
	 * @return a formatted String of the SURVIVABLE paths down the mountain, after checking for obstacles and food
	 */
	public String hikerSurvivalPaths (ArrayList<ArrayList<RestStop>> livePaths) {
		//Set the strings as empty
		String actualPaths="";
		String potential="";
		//Iterate through the possible paths we found
		for(int i=0;i<livePaths.size();i++) {
			//we clear hikers supplies each time since this represents
			//the start of each path
			this.hikerSupplies.clear();
			//clear the potential paths string
			potential="";
			for(int j=0;j<livePaths.get(i).size();j++) {
				//Add all the supplies in the RestStop to hiker supplies
				this.hikerSupplies.addAll(livePaths.get(i).get(j).getSupplies());
				//If there is a river obstacle at this restStop....
				if(livePaths.get(i).get(j).getObstacles().contains("river")) {
					//If we don't have a raft this path leads to our doom so we break the inner loop
					if(!(this.hikerSupplies.contains("raft"))){
						break;
					}
					//otherwise we have a raft, but we remove it since we used it
					else {
						this.hikerSupplies.remove("raft");	
					}
				}
				//If this path has a fallen tree.....
				if(livePaths.get(i).get(j).getObstacles().contains("fallen tree")) {
					//and we don't have an axe, we break the loop because we just died.
					if(!(this.hikerSupplies.contains("axe"))){
						break;
					}
					//otherwise, we have an axe and we move on, but we remove it from our supplies
					else {
						this.hikerSupplies.remove("axe");
					}
				}
				//now we update the string with the String label of the RestStops
				potential=potential+livePaths.get(i).get(j).getLabel()+" ";
				//If we get to the final RestStop successfully, we add the potential paths String to the 
				//actualPaths String.  Then we break it because we don't want to eat more food (which comes after)
				if(j==livePaths.get(i).size()-1) {
					potential=potential.trim();
					actualPaths=actualPaths+potential+"\n";
					break;
				}
				//If we have food in our bag, we must eat it each time we move on to the next RestStop
				if(this.hikerSupplies.contains("food")) {
					this.hikerSupplies.remove("food");
				}
				//if not, we died of hunger so we break the loop.
				else {
					break;
				}
			}
		}
		//Trim for extra space
		actualPaths=actualPaths.trim();
		return actualPaths;
	}
}
