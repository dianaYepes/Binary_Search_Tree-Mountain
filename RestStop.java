package project5;
import java.util.ArrayList;

/** This class is our RestStop class
 * it represents the "RestStops" in the mountain
 * it implements the comparable interface
 * this is meant to be the data within each node of our mountain BST
 * each RestStop has its designated supplies and obstacles
 *
* @author Diana Yepes!
*/

public class RestStop implements Comparable<Object> {
	//private fields
	//includes lists which store our RestStop obstacles and supplies
	private String label;
	private ArrayList<String> supplies = new ArrayList<String>();
	private ArrayList<String> obstacles = new ArrayList<String>();
	
	/**
	 *Constructor for our RestStop
	 *@param String label: the label/name of our RestStop
	 */
	public RestStop(String label) {
		this.label = label;
	}

	/**
	 *getter method for the RestStop label 
	 * @return String label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 *setter method for RestStop label 
	 * @param new String label to be set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 *getter method for our RestStop supplies
	 * @return an arraylist of all the supplies at this RestStop
	 */
	public ArrayList<String> getSupplies() {
		return supplies;
	}
	/**
	 *setter method for RestStop supplies 
	 * @param new arraylist to be set as new supplies
	 */
	public void setSupplies(ArrayList<String> supplies) {
		this.supplies = supplies;
	}
	/**
	 *getter method for our RestStop obstacles
	 * @return an arraylist of all the obstacles at this RestStop
	 */
	public ArrayList<String> getObstacles() {
		return obstacles;
	}
	/**
	 *setter method for RestStop obstacles
	 * @param new arraylist to be set as new obstacles
	 */
	public void setObstacles(ArrayList<String> obstacles) {
		this.obstacles = obstacles;
	}



	/**
	 *Method from interface
	 *This method is supposed compare the RestStops based of their label fields
	 *Since label is a String it compares alphanumerically
	 *@param  o: object(RestStop) to be compared to this RestStop
	 *@return a negative, positive or 0 int depending on alphanumerical comparison
	 */
	public int compareTo(Object o) {
		//if o is null or not a RestStop type we can't compare
		if (!(o instanceof RestStop) || o==null){
			throw new IllegalArgumentException ("bad input must be type RestStop");
		}
		//otherwise wee cast it and compare it
		RestStop cStop=(RestStop) o;
		return(this.getLabel().compareTo(cStop.getLabel()));
		
	}
}
