package bowling.domain.frame;

public class FrameLinkedList {
    private static final int INITIAL_SIZE = 1;

    private int size;
    private AbstractFrame head;

    FrameLinkedList() {
        this.head = new NormalFrame();
        this.size = INITIAL_SIZE;
    }

    void add(AbstractFrame newAbstractFrame) {
        AbstractFrame tempHead = this.head;
        while (tempHead.next != null) {
            tempHead = tempHead.next;
        }
        tempHead.next = newAbstractFrame;
        this.size++;
    }

    AbstractFrame get() {
        return head;
    }

    public AbstractFrame get(int index) {
        return search(index);
    }

    private AbstractFrame search(int index) {
        AbstractFrame before = head;
        for (int i = 0; i < index; i++) {
            before = before.next;
        }
        return before;
    }

    public int size() {
        return size;
    }
}