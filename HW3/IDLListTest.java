package hw3;

public class IDLListTest {

	public static void main(String[] args) {
		
		IDLList<Integer> idlList = new IDLList<Integer>();
		//test add()
		idlList.add(2);
        System.out.println("The first element in the list is: " + idlList.toString() + "\n");
        
        idlList.add(0,1);
        System.out.println("After add 1 at the head, the list becomes " + idlList.toString() + "\n");
        
        idlList.add(2,3);
        System.out.println("After add 3 at index 2, the list becomes " + idlList.toString() + "\n");
        
        //test append
        idlList.append(4);
        System.out.println("After append, the list is : " + idlList.toString() + "\n");
        
        //test get(),getHead(),getLast()
        System.out.println("Get element of position 1 in list : " + idlList.get(1) + "\n");

        System.out.println("Get head object in list: " + idlList.getHead() + "\n");

        System.out.println("Get tail object in list: " + idlList.getLast() + "\n");
        
        System.out.println("The current list size is: " + idlList.size() + "\n");
        
        //test remove()
        System.out.println("The removed head element is: " + idlList.remove());
        System.out.println("After remove, the current list is: " + idlList.toString() + "\n");
        
        //test removeLast()
        System.out.println("The removed tail element is: " + idlList.removeLast());
        System.out.println("After remove, the current list is: " + idlList.toString() + "\n");
        
        //test removeAt()
        System.out.println("The removed element is: " + idlList.removeAt(0));
        System.out.println("After remove, the current list is: " + idlList.toString() + "\n");
        
        //test remove(elem)
        idlList.remove(2);
        System.out.println("After removed the elem, now the list is: "
                + idlList.toString());
	}

}
