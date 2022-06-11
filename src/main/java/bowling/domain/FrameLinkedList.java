package bowling.domain;

public class FrameLinkedList {
    private static final int INITIAL_SIZE = 1;

    private int size;
    private Frame head;

    FrameLinkedList() {
        this.head = new NormalFrame();
        this.size = INITIAL_SIZE;
    }

    void add(Frame newFrame) {
        Frame tempHead = this.head;
        while (tempHead.next != null) {
            tempHead = tempHead.next;
        }
        tempHead.next = newFrame;
        this.size++;
    }

    Frame get() {
        return head;
    }

    public Frame get(int index) {
        return search(index);
    }

    private Frame search(int index) {
        Frame before = head;
        for (int i = 0; i < index; i++) {
            before = before.next;
        }
        return before;
    }

    public int size() {
        return size;
    }
}