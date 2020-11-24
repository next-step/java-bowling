package step2.domain;

public class Frames {
    public static final int MAXIMUM_FRAME_RANGE = 10;

    private final int size;
    private final Frame head;
    private final Frame tail;


    public static Builder Builder() {
        return new Builder();
    }

    public int size() {
        return size;
    }

    public Frame getHead() {
        return head;
    }

    public Frame getTail() {
        return tail;
    }

    public static class Builder {

        private int size;
        private Frame head;
        private Frame tail;

        public Builder() {
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder head(Frame head) {
            this.head = head;
            return this;
        }

        public Builder tail(Frame tail) {
            this.tail = tail;
            return this;
        }

        public Frames build() {
            return new Frames(this);
        }
    }

    public Frames(Builder builder) {
        this.size = builder.size;
        this.head = builder.head;
        this.tail = builder.tail;
    }
}
