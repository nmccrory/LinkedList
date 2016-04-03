package linkedlist;

import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class LinkedList implements MyList{
    private Node head;
    private Node tail;
    public LinkedList(){

    }
    public LinkedList(int[] arr){
        for(int x : arr){
            add(x);
        }
    }
    
    @Override
    public Node getStart() {
        return head;
    }

    @Override
    public Node getEnd() {
        return tail;
    }

    @Override
    public void add(Integer i) {
        Node n = new Node(i, tail, null);
        if(head == null){
            head = n;
        }
        if(tail != null){
            tail.next = n;
        }
        tail = n;
    }

    @Override
    public MyList split(int pos) {
        Node currentNode = head;
        int count = 0;
        LinkedList splitList = new LinkedList();
        for(int i=0;i<pos; i++){
            currentNode = currentNode.next;
        }

        Node tempNode = currentNode.next;
        currentNode.next = null;
        while(tempNode != null){
            splitList.add(tempNode.data);
            tempNode = tempNode.next;
        }
        return splitList;
    }

    @Override
    public void add(int pos, MyList list) {
        Node currentNode = head;
        int count = 1;
        
        while(currentNode != null && count < pos){
            count++;
            currentNode = currentNode.next;
        }
        if(pos == 0){
            head = list.getStart();
            list.getEnd().next = currentNode;
        }else{
            Node temp = currentNode.next;
            currentNode.next = list.getStart();
            list.getEnd().next = temp;
        }
    }

    @Override
    public ArrayList<Integer> getData() {
        Node currentNode = head;
        ArrayList al = new ArrayList<>();
        while(currentNode != null){
            al.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return al;
    }
    
}
