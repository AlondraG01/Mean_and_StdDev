
public class LinkedList<Double> {

	// data members
	private Node head;
	private Node tail;

	// constructor
	public LinkedList() {
		head = null;
		tail = null;
	}

	// adding into the linked list
	public void push(int data) {		
		// asserts that there is something to push
		assert (true);

		// CREATE TEMP NODE
		Node temp = new Node(data);

		// SCENARIO 1: LINKED LIST IS EMPTY
		if (head == null) {
			head = temp;
			tail = temp;
		}
		// SCENARIO 2: ADD THE NODE TO THE END
		else {
			temp.prev = tail;
			tail.next = temp;
			tail = temp;
		}
		// asserts that the node was successfully added
		assert (tail.data == data) : "Node not added to the back"; 
	}

	public static void printList(LinkedList list) {
		Node currNode = list.head;
		System.out.print("Linked List: ");

		while (currNode != null) {
			System.out.print(currNode.data + " ");
			currNode = currNode.next;
		}
		System.out.println();
	}
	
	//gets the length of the linked list
	public static int countNodes(LinkedList list) {
	    Node temp1 = list.head;
	    int i = 0;
	    while(temp1 != null) {
	      i++;
	      temp1 = temp1.next;
	    }
	    return i;  
	  } 
	
	//gets the sum of the linked list
	public static int sum (LinkedList list) {
		Node temp2 = list.head;
	    int sum = 0;
	    while(temp2 != null) {
	    	sum += temp2.data;
	    	temp2 = temp2.next;
	    }
	    return sum;
	}
	
	public static double standardDeviation(LinkedList list, double mean) {
		Node temp = list.head;
		double sD = 0.0;
		
		while(temp != null) {
			sD += Math.pow(temp.data - mean, 2);
			temp = temp.next;
		}
		return sD;
	}
	
	public boolean isEmpty() {
		return head == null && tail == null;
	}

}
