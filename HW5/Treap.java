package hw5;
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	//Create a private static inner class Node<E> of the Treap class
	private static class Node<E>{
		//Data fields
		public E data; 
        public int priority; 
        public Node<E> left;
        public Node<E> right;
        
        /**
		 * Constructor setting the node's data, priority, right and left
		 * @param data
		 * @param priority
		 * @throws Exception 
		 */
        public Node(E data, int priority) {
        	if(data == null) {
        		throw new NullPointerException();
        	}
        	else {
        		this.data = data;
        		this.priority = priority;
        		this.right = null;
        		this.left = null;
        	}
        }
    
        /**
         * performs a right rotation
         * @return a new root of the result.
         */
        public Node<E> rotateRight() {
        	Node<E> temp = this;
			if (this.left == null) {
				return temp;
			}
			else{
				Node<E> L = temp.left;
				temp.left = L.right;
				L.right = temp;
				// new root is the left node
				return L;
			}
        }
       
        /**
         * performs a left rotation
         * @return a new root of the result.
         */
        public Node<E> rotateLeft() {
        	Node<E> temp = this;
			if (this.right == null) {
				return temp;
			}
			else{
				Node<E> R = temp.right;
				temp.right = R.left;
				R.left = temp;
				// new root is the right node
				return R;
			}
        }
        
        public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority + ")";
		}     
        
	}
	
	private Random priorityGenerator;    
    private Node<E> root;                
	
    /**
	 * creates an empty treap
	 */
    public Treap() {
        root = null;
        priorityGenerator = new Random();
    }
    //creates an empty treap
    /**
   	 * creates an empty treap
   	 * @param seed
   	 */
    public Treap(long seed) {
        root = null;
        priorityGenerator = new Random(seed);
    }
	
    /**
	 * Add operation
	 * @param key
	 * @return returns true, if a node with the key was successfully added to the treap. 
	 * If there is already a node containing the given key, the method returns false and does not modify the treap.
	 */
    public boolean add(E key) {
		if(key == null){
			return false;
		}
		else if(find(key) == true){ 
			return false;
		}
		else {
			return add(key, priorityGenerator.nextInt());		
		}
    }

    /**
	 * Adds a value to the tree using key and a specific integer priority
	 * @param key 
	 * @param priority 
	 * @return a boolean 
	 */

    public boolean add(E key, int priority){
		if(key == null){
			return false;
		}
		if(find(key) == true){ 
			return false;
		}
		if (root == null) {
			Node<E> newRoot = new Node<E>(key, priority);
			root = newRoot;
			return true;
		}
		else {
			Stack<Node<E>> traversePath = new Stack<Node<E>>();	
			Node<E> currentNode = root;
			
			while(root != null) {
				int equality = key.compareTo(currentNode.data);
				traversePath.push(currentNode);
				
				if(equality < 0 && currentNode.left == null) //add to left subtree
				{
						Node<E> newnode = new Node<E>(key,priority);
						currentNode.left = newnode;
						reheap(traversePath, newnode);
						return true;
				}	
				else if(equality < 0 && currentNode.left != null){
					currentNode = currentNode.left;
				}
				
				if(equality > 0 && currentNode.right == null) //add to right subtree
				{
						Node<E> newnode = new Node<E>(key,priority);
						currentNode.right = newnode;
						reheap(traversePath, newnode);
						return true;
					
				}
				else if(equality > 0 && currentNode.right != null){
					currentNode = currentNode.right;
				}
			}
			return false;
			
		}
    }
    
    //a helper function reheap to restore the heap invariant.
    /**
	 * a helper function reheap (with appropriate parameters that should include the stack) 
	 * to restore the heap invariant.
	 * @param traversePath
	 * @param newNode
	 */
  	public void reheap(Stack<Node<E>> traversePath, Node<E> newNode) {
  		
  		while (!traversePath.isEmpty()) {
  			
  			Node<E> parent = traversePath.pop();
  			int equality = parent.data.compareTo(newNode.data);
  			
  			if (parent.priority < newNode.priority) { 
  				
  				//if parent data is bigger than newnode data
  				if (equality > 0) {
  					newNode = parent.rotateRight();
  				}
  				//if parent data is less than newnode data
  				else if(equality < 0) {
  					newNode = parent.rotateLeft();
  				}
  				
  				//if stack is empty, root node will be new node
  				if(traversePath.isEmpty()) {
  					this.root = newNode;
  				}
  				//if stack is not empty, arrange the newNode with left and right attachments
  				else if(!traversePath.isEmpty()) {
  					Node<E> nnode = traversePath.peek();
  					
  					if(nnode.right == parent) {
  						nnode.right = newNode;
  					}
  					
  					else if(nnode.left == parent){
  						nnode.left = newNode;	
  					}
  				
  				}
  			}
  			else {
  				break;
  			}
  		}
  	}
  	
  	/**
     * Deletes the node with the given key.
     * @param key 
     * @return a boolean
     */
  	public boolean delete(E key) {
		if (root == null || find(key) == false) {
			return false;
		} else {
			root = delete(root, key);
			return true;
		}
	}
  	/**
	 * deletes the node with the given key from the treap.
	 * @param root
	 * @param key
	 * @return subRoot
	 */
  	private Node<E> delete(Node<E> subRoot, E key) {
        if (subRoot != null) {
            int comp = key.compareTo(subRoot.data);
            if (comp > 0) {
                // if the key is bigger than the root, delete the right
                subRoot.right = delete(subRoot.right, key);
            } else if (comp < 0) {
                // if the key is lesss than the root, delete the left
                subRoot.left = delete(subRoot.left, key);
            } else { // found key
                if (subRoot.left == null) {
                    return subRoot.right;
                } else if (subRoot.right == null) {
                    return subRoot.left;
                } else {
                    // both children nodes are not null
                    Node<E> temp = subRoot.right;
                    while (temp.left != null) {
                        temp = temp.left;
                    }
                    subRoot.data = temp.data;
                    subRoot.right = delete(subRoot.right, subRoot.data);
                }
            }
        }
        return subRoot;
    }
    
  	/**
	 * Finds a node with the given key in the treap rooted at root 
	 * and returns true if it finds it and false otherwise.
	 * @param key
	 * @return a boolean
	 */
  	public boolean find (E key) {
  		boolean result = find(root,key);
  		return result;
  	}
  	/**
	 * Finds a node with the given key in the treap rooted at root 
	 * and returns true if it finds it and false otherwise.
	 * @param root
	 * @param key
	 * @return a boolean
	 */
  	private boolean find (Node <E> root , E key ) {
  		
  		Node<E> temp = root;
  		if(temp == null) {
  			return false;
  		}
  		if(key == null) {
  			return false;
  		}
  		int comp = key.compareTo(temp.data);
  		if(comp < 0) {
  			return find(temp.left,key);
  		}
  		else if(comp > 0){
  			return find(temp.right,key);	
  		}
  		else {
  			return true;
  		}  
  			
  	}
  	
  	/**
	 * Carries out a pre-order traversal of the tree and returns a representation of the nodes as a string.
	 * This is a OVERRIDE
	 */

  	public String toString(){
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
  	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
			return;
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
  	
  	
  	
	

	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31); 
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83); 
		testTree.add(7,26);
		System.out.println("----The original tree----");
		System.out.println(testTree);
		System.out.println();
		System.out.println("----After delete the original root----");
		testTree.delete(1);
		System.out.println(testTree);
		System.out.println();
		System.out.println("----After add the original root----");
		testTree.add(1,84);
		System.out.println(testTree);
		System.out.println("test find(6): "+testTree.find(6));
		System.out.println("test find(100): "+testTree.find(100));

	}

}
