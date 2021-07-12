package project5;


/** This class is our BST implementation
 * it is not generic as it only accepts RestStop data items in the nodes
 *it implements comparable to compare node data 
 *relevant methods are included
 *
* @author Diana Yepes!
*/

public class BSTMountain {
	
	/**
	 *The inner node class
	 *all fields are private
	 *We also include references to left right child nodes
	 */
	class BSTNode implements Comparable <BSTNode>{
		private RestStop data;
		private BSTNode left;	
		private BSTNode right;
		
		/**
		 *getter method for the node data 
		 * @return data
		 */
		public RestStop getData() {
			return data;
		}
		/**
		 *setter method for the node data 
		 *@param data: RestStop to be set
		 */
		public void setData(RestStop data) {
			this.data = data;
		}
		
		/**
		 *getter method for left child node 
		 * @return left child node reference
		 */
		public BSTNode getLeft() {
			return left;
		}
		/**
		 *setter method for left child node
		 *@param new left child reference
		 */
		public void setLeft(BSTNode left) {
			this.left = left;
		}
		
		/**
		 *getter method for right child node 
		 * @return right child node reference
		 */
		public BSTNode getRight() {
			return right;
		}
		/**
		 *setter method for right child node
		 *@param new right child reference
		 */
		public void setRight(BSTNode right) {
			this.right = right;
		}
		
		
		/**
		 *Constructor for our inner node
		 *@param RestStop object that will be nodes "data"
		 */
		public BSTNode(RestStop data) {
			this.data=data;
		}
		
		/**
		 *method that compares the data in the nodes(RestStop objects)
		 *@param other: other node to be compared
		 *@return negative,0,or positive int depending on how the RestStops compare
		 */
		public int compareTo(BSTNode other) {
			return this.data.compareTo ( other.data);
		}
	}
	
	//private fields for bst 
	private BSTNode root;
	private boolean added;
	
	/**
	 * Wrapper method for our recursive add method 
	 * @param data: RestStop to be added to node 
	 * @return boolean
	 */
	public boolean add ( RestStop data ) {
		//this means tree is empty so we create root node
        if (root == null) {
            this.root = new BSTNode(data);
            return true;
        //otherwise, call recursive add method    
        } else {
            added = recAdd(data, root); 
        }

         return added; 
    }
	/**
	 * recursive method to add our nodes
	 * Nodes are added depending on the RestStop compareTo method (which compares the RestStop labels alphanumerically)
	 * @param data,node: RestStop to be added to some node, and the node we will recurse on.
	 * @return boolean
	 */
    public boolean recAdd(RestStop data, BSTNode node) {
    	//if compareTo method returns negative int, we know it is smaller than the node, so left child
        if (data.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new BSTNode(data);
                return true;
            }
            return recAdd(data, node.left);  
         //otherwise, compareTo method returns positive int, so we know it is larger than the node, so right child
        } else if (data.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new BSTNode(data);
                return true;
            }
            return recAdd(data, node.right);
        } else 
        return false;
    }
    
    /**
	 *getter method for root node of tree 
	 * @return root node
	 */
    public BSTNode getRoot() {
		return root;
	}
    /**
	 *setter method for root node 
	 * @param new node to be set
	 */
	public void setRoot(BSTNode root) {
		this.root = root;
	}
	/**
	 *getter method for isAdded field  
	 * @return boolean true or false
	 */
	public boolean isAdded() {
		return added;
	}
	/**
	 *setter method for isAdded field  
	 * @param boolean value to be set
	 */
	public void setAdded(boolean added) {
		this.added = added;
	}
	
	/**
	 * recursive method to find the depth of tree
	 * @param node: node (the root most likely) in which we start (first level)
	 * @return int: the depth of the bst
	 */
    int maxDepth(BSTNode node) {
    	//if node is null it means tree has no nodes so depth is 0
        if (node == null)
            return 0;
        //otherwise recurse through nodes 
        else{  
        	int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
            //return updated depth
            if (lDepth > rDepth) {
                return (lDepth + 1);
            }
            else {
            	return (rDepth + 1);
            }
        }
    }
}
