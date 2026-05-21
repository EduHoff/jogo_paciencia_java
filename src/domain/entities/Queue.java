package domain.entities;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public boolean isEmpty(){
		return head == null;
	}
    
    public void enqueue(T value){
        Node<T> new_node = new Node<>(value);
        
        if(this.isEmpty()){
            head = new_node;
            tail = new_node;
            size++;
            return;
        }

        tail.setNext(new_node);
        tail = new_node;
        size++;
    }

    public void dequeue(){
        if(this.isEmpty()) return;

        head = head.getNext();
        if(head == null) tail = null;
        size--;
    }

    public void printList() {
		if (this.isEmpty()) {
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

    public T peek(){
        if(head == null) return null;
        return head.getValue();
    }

    //Método exclusívo para o Jogo da Paciência e por isso não se encontra na lista de Estrutura de Dados
    public void printLog() {
        if (head == null) {
            System.out.println("  Nenhuma ação registrada ainda.");
            return;
        }
        
        Node<T> aux = head;
        while (aux != null) {
            System.out.println("  > " + aux.getValue().toString());
            aux = aux.getNext();
        }
    }
}
