package com.stephull.projects.koreanbuildingapp.model;

public class LinkedList {
    
    private LinkedListNode head;
    private LinkedListNode curr;

    public LinkedList() {}

    public LinkedList(
        LinkedListNode head,
        LinkedListNode curr
    ) {
        this.head = head;
        this.curr = curr;
    }

    public LinkedListNode getHead() {
        return this.head;
    }

    public void setHead(LinkedListNode newHead) {
        this.head = newHead;
    }

    public LinkedListNode getCurr() {
        return this.curr;
    }

    public void setCurr(LinkedListNode newCurr) {
        this.curr = newCurr;
    }

    // other stuff

    public void addNode(KoreanBuild newData) {
        LinkedListNode newNode = new LinkedListNode();
        newNode.setData(newData);

        if (head == null) {
            head = newNode;
            curr = newNode;
        } else {
            curr.setNext(newNode);
            curr = newNode;
        }
    }

    public void moveForward() {
        if (curr != null && curr.getNext() != null) {
            curr = curr.getNext();
        }
    }

    public void moveBackward() {
        if (curr != null && curr != head) {
            LinkedListNode prev = head;
            while (prev.getNext() != curr) {
                prev = prev.getNext();
            }
            curr = prev;
        }
    }

}
