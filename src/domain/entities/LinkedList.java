package domain.entities;

public class LinkedList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int size=0;
	
	public boolean isEmpty(){
		return head == null;
	}

	public void addFirst(T value) {
		Node<T> new_node  = new Node<>(value);
		
		new_node.setNext(head);
		if(this.isEmpty()){
			head = new_node;
			tail = new_node;
			size++;
			return;
		}

		head.setPrev(new_node);
		head = new_node;
		size++;
	}
	
	public void addLast(T value) {
		Node<T> new_node = new Node<>(value);
		
		if (this.isEmpty()) {
            head = new_node;
			tail = new_node;
			size++;
            return;
        }
		
		tail.setNext(new_node);
		new_node.setPrev(tail);
		tail = new_node;
		size++;
	}

	public void addMiddle(int index, T value) {
		if(index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		
		if(index == 0) {
			addFirst(value);
			return;
		}

		if(index == size){
			addLast(value);
			return;
		}

		Node<T> new_node = new Node<>(value);
		
		Node<T> aux = head;
		int count = 0;
		while(count != index-1) {
			aux = aux.getNext();
			count++;
		}

		new_node.setNext(aux.getNext());
		new_node.setPrev(aux);
		aux.getNext().setPrev(new_node);
		aux.setNext(new_node);
		size++;
	}

	public void removeFirst() {
		if(this.isEmpty()) return;

		if(head.getNext() == null){
			head = null;
			tail = null;
			size--;
			return;
		}
		
		head = head.getNext();
		head.setPrev(null);
		size--;
	}

	public void removeLast() {
		if(this.isEmpty()) return;

		if(head.getNext() == null){
			head = null;
			tail = null;
			size--;
			return;
		}

		tail = tail.getPrev();
		tail.setNext(null);
		size--;
	}

	public void removeMiddle(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		
		if(index == 0) {
			removeFirst();
			return;
		}

		if(index == size-1){
			removeLast();
			return;
		}
		
		Node<T> aux = head;
		int count = 0;
		while(count != index-1) {
			aux = aux.getNext();
			count++;
		}

		aux.getNext().getNext().setPrev(aux);
		aux.setNext(aux.getNext().getNext());
		size--;
	}

	public T get(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		
		Node<T> aux = head;
		int count = 0;
		while (count != index) {
			aux = aux.getNext();
			count++;
		}
		return aux.getValue();
	}
	
	public void printList() {
		if (head == null) {
	        System.out.println("{}");
	        return;
	    }
		
		Node<T> aux = head;
		while(aux != null) {
			System.out.print(aux.getValue().toString());
			if(aux.getNext() != null) System.out.print(" -> ");
			aux = aux.getNext();
		}
		System.out.println();
	}

	public int length() {
		return size;
	}
}
