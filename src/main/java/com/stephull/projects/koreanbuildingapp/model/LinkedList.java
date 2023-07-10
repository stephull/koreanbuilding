package com.stephull.projects.koreanbuildingapp.model;

public class LinkedList<E> {
    
    private Node<E> head;
    private Node<E> tail;

    public LinkedList() {}

    public LinkedList(
        Node<E> head,
        Node<E> tail
    ) {
        this.head = head;
        this.tail = tail;
    }

    public Node<E> getHead() {
        return this.head;
    }

    public void setHead(Node<E> newHead) {
        this.head = newHead;
    }

    public Node<E> getTail() {
        return this.tail;
    }

    public void setTail(Node<E> newTail) {
        this.tail = newTail;
    }

    public void add(E element) {
        Node<E> newNode = new Node<E>();
        newNode.setData(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public boolean remove(E element) {
        Node<E> temp = head;

        while (temp != null) {
            if (temp.getData().equals(element)) {
                Node<E> p = temp.getPrev(), n = temp.getNext();
                if (p != null) {
                    p.setNext(n);
                } else {
                    head = n;
                }

                if (n != null) {
                    n.setPrev(p);
                } else {
                    tail = p;
                }

                return true;
            }
            temp = temp.getNext();
        }

        return false;
    }

    public void moveForward() {
        if (tail != null && tail.getNext() != null) {
            tail = tail.getNext();
        }
    }

    public void moveBackward() {
        if (tail != null && tail != head) {
            Node<E> prev = head;
            while (prev.getNext() != tail) {
                prev = prev.getNext();
            }
            tail = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> temp = head;
        
        sb.append('\n');

        while (temp != null) {
            sb.append(temp);
            if (temp.getNext() != null) {
                sb.append(" -> ");
            } 
            temp = temp.getNext();
        }

        sb.append('\n');

        return sb.toString();
    }

}

class Node<E> {
    
    private Node<E> next;
    private Node<E> prev;
    private E data;

    public Node() {}

    public Node(
        Node<E> prev,
        Node<E> next,
        E data
    ) {
        this.next = next;
        this.prev = prev;
        this.data = data;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setNext(Node<E> newNext) {
        this.next = newNext;
    }

    public Node<E> getPrev() {
        return this.prev;
    }

    public void setPrev(Node<E> newPrev) {
        this.prev = newPrev;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E newData) {
        this.data = newData;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Data: %s
            ]   
            """,
            data
        );
        return ret.indent(2);
    }

}

class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<KoreanBuild> list = new LinkedList<KoreanBuild>();

        KoreanSpeechCluster base1 = new KoreanSpeechCluster(
            "ㄴ", "n", SpeechType.END_CONSONANT
        );
        KoreanSpeechCluster base2 = new KoreanSpeechCluster(
            "ㅏ", "ah", SpeechType.VOWEL
        );
        KoreanSpeechCluster base3 = new KoreanSpeechCluster(
            "ㄴ", "n", SpeechType.END_CONSONANT
        );

        KoreanBuild kb1 = new KoreanBuild("한", base1, "1234");
        KoreanBuild kb2 = new KoreanBuild("라", base2, "1234");
        KoreanBuild kb3 = new KoreanBuild("산", base3, "1234");

        list.add(kb1);
        list.add(kb2);
        list.add(kb3);
        
        System.out.println("FINAL LINKED LIST: " + list.toString());
    }
}