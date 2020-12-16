package bowling.domain.frames;

public class FirstAndLastFrame {
    private final Frame first;
    private final Frame last;

    private FirstAndLastFrame(Frame first, Frame last) {
        this.first = first;
        this.last = last;
    }

    public static FirstAndLastFrame of(Frame first, Frame last) {
        return new FirstAndLastFrame(first, last);
    }

    public Frame getFirst() {
        return first;
    }

    public Frame getLast() {
        return last;
    }
}
