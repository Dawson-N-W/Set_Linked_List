package Project_1;

class LinkedList<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    LinkedList(T[] arr){
        for (T t : arr)
            add(t);
    }


    LinkedList(){}

    public int getSize(){
        return size;
    }

    //add(T t) only appends to the end of the list using the index as the last element
    //add(int index, T t) adds to the list at the specified index
    public void add(T t){
        //add elements to end of the list
        addLast(t);
    }

    public void addFirst(T t){
        //element must not exist in the list
        if(exists(t)){
            return;
        }

        Node<T> newNode = new Node<>(t);
        newNode.next = head;
        head = newNode;
        size++;

        if(tail == null){
            tail = head;
        }
    }

    public void addLast(T t){
        //element must not exist in the list
        if(exists(t)){
            return;
        }

        Node<T> newNode = new Node<>(t);
        if(tail == null){
            head = newNode;
        }
        else{
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean exists(T t){
        //traverse the list to see if the element exists
        Node<T> current = head;
        while(current != null){
            //if the element exists, return true
            if(current.data.equals(t)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public Node<T> getNode(T data) {
        //traverse a list to find the element and return the node
        Node<T> current = head;
        while (current != null) {
            //if the element's data field matches the specified data, return the node
            if (current.data == data)
                return current;
            current = current.next;
        }
        return null;
    }

    //add a new node object to the list
    public void addNode(Node<T> node){
        if(exists(node.data)){
            return;
        }
        if(size > 0){
            tail.next = node;
            tail = node;
        }
        else{
            head = node;
            tail = node;
        }
        size++;
    }

    public void add(int index, T t){
        //Check to see if element already exists
        if(exists(t))
            return;
        //Check to see if index is out of bounds
        if(index <= 0){
            addFirst(t);
        }
        else if(index >= getSize()){
            addLast(t);
        }
        else{
            //traverse the list to find the node before the specified index
            Node<T> current = head;
            for(int i = 0; i < index - 1; i++){
                current = current.next;
            }
            //create a new node with the specified data
             Node<T> newNode = new Node<>(t);
            //set the new node's next field to the current node's next field
             newNode.next = current.next;
             current.next = newNode;
             size++;
        }
    }

    public void remove(T data){
        //if list is empty and the node for removal must exist
        if(getSize() > 0){
            Node<T> current = head;
            if(current.data == data){
                head = current.next;
                if(head == null)
                    tail = null;
                size--;
                return;
            }
            //current will be the element before what you want to remove
            //so current will never be on the tail, but the element before
            //the tail of trying to removal the last element
            while(current.next != null){
                if(current.next.data == data){
                    current.next = current.next.next;
                    if(current.next == null)
                        tail = current;
                    size--;
                    return;
                }
                else {
                    current = current.next;
                }
            }
        }
    }

    public void removeIndex(int index){
        if(getSize() > 0 && index >= 0 && index <= size - 1){
            //check to see if the removed element is the head
            if(index == 0){
                head = head.next;
                if(head == null){
                    tail = null;
                }
                size--;
                return;
            }
            //element is not the head, so will traverse until at the element behind the one to removed
            Node<T> current = head;
            for(int i = 0; i < index - 1; i++){
                current = current.next;
            }
            current.next = current.next.next;
            if(current.next == null)
                tail = current;
            size--;
        }
    }

    public LinkedList<T> union(LinkedList<T> list1, LinkedList<T> list2){
        Node<T> current = list2.head;
        //add all nodes from list2 into list1, and then return list1
        while(current != null){
            list1.add(current.data);
            current = current.next;
        }
        return list1;
    }

    //cloneNode clones a node to the list, without checking if it exists or not.
    //It only appends to the end of the list
    void cloneNode(Node<T> node){
        if(head == null){
            head = new Node<>(node.data);
            tail = head;
        }
        else{
            tail.next = new Node<>(node.data);
            tail = tail.next;
        }
        size++;
    }

    public LinkedList<T> intersection(LinkedList<T> list1, LinkedList<T> list2){
        //Linked List intersection that will be returned
        LinkedList<T> list3 = new LinkedList<>();
        Node<T> current = list1.head;
        Node<T> current2 = list2.head;
        //traverse list1
        while(current != null){
            //traverse list2
            while(current2 != null){
                //if the data fields match, clone the node into list3
                if(current.data == current2.data){
                    list3.cloneNode(current);
                    break;
                }
                current2 = current2.next;
            }
            current = current.next;
            current2 = list2.head;
        }
        return list3;
    }

    //returns a string representation of the list when printing
    public String toString(){
        StringBuilder str = new StringBuilder();
        Node<T> current = head;
        while(current != null){
            str.append(current.data).append(" ");
            current = current.next;
        }
        return str.toString();
    }
}

class Node<T> {
    T data;
    Node<T> next;
    public Node(T data) {
        this.data = data;
    }

    public String toString(){
        return "" + data;
    }
}



