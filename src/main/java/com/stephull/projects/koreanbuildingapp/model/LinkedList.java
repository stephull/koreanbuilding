package com.stephull.projects.koreanbuildingapp.model;

public class LinkedList {
    
    private LinkedListNode head;
    private LinkedListNode current;

    public LinkedList() {}

    public LinkedList(
        LinkedListNode head,
        LinkedListNode current
    ) {
        this.head = head;
        this.current = current;
    }

    public LinkedListNode getHead() {
        return this.head;
    }

    public void setHead(LinkedListNode newHead) {
        this.head = newHead;
    }

    public LinkedListNode getCurrent() {
        return this.current;
    }

    public void setCurrent(LinkedListNode newCurrent) {
        this.current = newCurrent;
    }

    // other stuff

    public void add(KoreanBuild newData) {
        LinkedListNode newNode = new LinkedListNode();
        newNode.setData(newData);

        if (head == null) {
            head = newNode;
            current = newNode;
        } else {
            current.setNext(newNode);
            current = newNode;
        }
    }

    public void moveForward() {
        if (current != null && current.getNext() != null) {
            current = current.getNext();
        }
    }

    public void moveBackward() {
        if (current != null && current != head) {
            LinkedListNode prev = head;
            while (prev.getNext() != current) {
                prev = prev.getNext();
            }
            current = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedListNode temp = head;
        int count = 0;
        
        sb.append('\n');

        while (temp != null) {
            sb.append("::: " + (++count) + " :::" + "\n");
            sb.append(temp.getData());
            if (temp.getNext() != null) {
                sb.append(" -> ");
            } 
            temp = temp.getNext();
        }

        sb.append('\n');

        return sb.toString();
    }

}

class LinkedListNode {
    
    private LinkedListNode next;
    private LinkedListNode prev;
    private KoreanBuild data;

    public LinkedListNode() {}

    public LinkedListNode(
        LinkedListNode prev,
        LinkedListNode next,
        KoreanBuild data
    ) {
        this.next = next;
        this.prev = prev;
        this.data = data;
    }

    public LinkedListNode getNext() {
        return this.next;
    }

    public void setNext(LinkedListNode newNext) {
        this.next = newNext;
    }

    public LinkedListNode getPrev() {
        return this.prev;
    }

    public void setPrev(LinkedListNode newPrev) {
        this.prev = newPrev;
    }

    public KoreanBuild getData() {
        return this.data;
    }

    public void setData(KoreanBuild newData) {
        this.data = newData;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Prev (KB ID): %s
                Next (KB ID): %s
                Data: %s
            ]   
            """,
            prev.data.getKbid(),
            next.data.getKbid(),
            data.toString()
        );
    }

}

class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        KoreanSpeech ks1 = new KoreanSpeech("ㅇ", "x", "ng");
        KoreanBuild kb1 = new KoreanBuild(
            ks1, "강", true
        );

        KoreanSpeech ks2 = new KoreanSpeech("ㄴ", "n");
        KoreanBuild kb2 = new KoreanBuild(
            ks2, "원", true
        );

        KoreanSpeech ks3 = new KoreanSpeech("ㅗ", "oh");
        KoreanBuild kb3 = new KoreanBuild(
            ks3, "도", true
        );

        list.add(kb1);
        list.add(kb2);
        list.add(kb3);
        
        System.out.println("FINAL LINKED LIST: " + list.toString());
    }
}