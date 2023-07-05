package com.stephull.projects.koreanbuildingapp.model;

public class LinkedListNode {
    
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
