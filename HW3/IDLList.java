package hw3;


import java.util.ArrayList;

public class IDLList<E> {
	
	private static class Node<E>{
		// three data fields
		
		E data;
		Node<E> next;
		Node<E> prev;
		
		//a constructor that creates a node holding elem
		private Node(E elem) {
			data = elem;
			next = null;
			prev = null;	
		}
		
		//a constructor that creates a node holding elem, with next as next and prev as prev.
		private Node(E elem, Node<E> next, Node<E> prev) {
			data = elem;
			this.next = next;
			this.prev = prev;
		}
	}
	//The class IDLList<E> should include the declaration of this inner private class Node<E>. 
	//Apart from that, it should have four data fields:
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//publicIDList(),that creates an empty double-linked list.
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<>();
	}
	
	//public boolean add (int index, E elem) that adds elem at position index
	public boolean add(int index, E elem) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else if(index == 0) {
			add(elem);
			
			return true;
		}
		else if(index == size) {
			append(elem);
			
			return true;
		}
		else {
			Node<E> node = indices.get(index);
			Node<E> newnode = new Node<E>(elem);
			//insert the new node;
			newnode.next = node;
			newnode.prev = node.prev;
			//replace the old node;
			node.prev.next = newnode;
			node.prev = newnode;
			//update the size and indices;
			size++;
			indices.add(index, newnode);
			
			return true;
		}
	}
	
	//public boolean add (E elem) that adds elem at the head
	public boolean add(E elem) {
		if(head == null) {
			Node<E> node = new Node<E>(elem);
			head = node;
			tail = node;
			size++;
			indices.add(head);
			
			return true;
		}
		else {
			Node<E> node = new Node<E>(elem);
			head.prev = node;
			node.next = head;
			head = node;
			size++;
			indices.add(0,node);
			
			return true;
		}
	}
	
	//public boolean append (E elem) that adds elem as the new last element of the list 
	public boolean append(E elem) {
		if(head == null) {
			add(elem);
			
			return true;
		}
		else {
			Node<E> node = new Node<E>(elem);
			tail.next = node;
			node.prev = tail;
			tail = node;
			size++;
			indices.add(node);
			
			return true;
		}
	}
	
	//public E get(int index) that returns the object at position index from the head.
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		  
		return (indices.get(index)).data;
	}
	
	public E getHead () {
		return head.data;
	}
	
	public E getLast () {
		return tail.data;
	}
	
	public int size() {
		return size;
	}
	
	//public E remove() that removes and returns the element at the head.
	public E remove() {
		Node<E> node = head;
		if(head != null) {
			head = head.next;
			head.prev = null;
			size--;
			indices.remove(0);
			
			return node.data;	
		}
		else {
			System.out.println("No node in this list.");
			return null;
		}
	}
	
	//public E removeLast() that removes and returns the element at the tail
	public E removeLast() {
		Node<E> node = tail;
		if(tail != null) {
			tail.prev.next = null;
			tail = tail.prev;
			size--;
			indices.remove(node);
			
			return node.data;	
		}
		else {
			System.out.println("No node in this list.");
			return null;
		}
	}
	
	//public E removeAt(int index) that removes and returns the element at the index index.
	public E removeAt(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else if(index == 0) {
			return remove();
		}
		else if(index == size - 1) {
			return removeLast();
		}
		else {
			Node<E> node = indices.get(index);
			node.prev.next = node.next;
			node.next.prev = node.prev;
			
			size--;
			indices.remove(index);
			
			return node.data;
		}
	}
	
	//public boolean remove (E elem) that removes the first occurrence of elem in the list and returns true. 
	//Return false if elem was not in the list.
	public boolean remove(E elem) {
		Node<E> node = new Node<E>(elem);
		for(int i = 0; i < size; i++) {
			if(indices.get(i).data == node.data) {
				removeAt(i);
				return true;
			}
		}
		System.out.println("NO such elem in the list");
		return false;
	}
	
	//public String toString(). That presents a string representation of the list.
	public String toString() {
		String res = "";
		Node<E> node = head;
		if(head == null) {
			System.out.print("No node in this list.");
		}
		while(node != null) {
			res = res + " "+ node.data;
			node = node.next;
		}
		return res;
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
