package bowling.domain.frame;

import java.util.Objects;

public class Frames {

    private final Frame head;
    private Frame tail;

    private Frames(Frame head, Frame tail) {
        this.head = head;
        this.tail = tail;
    }

    public static Frames readyFrames() {
        NormalFrame startFrame = NormalFrame.readyFrame(Round.FIRST);
        return of(startFrame, startFrame);
    }

    public static Frames of(Frame head, Frame tail) {
        return new Frames(head, tail);
    }

    public boolean isGameEnd() {
        return tail.isGameEnd();
    }

    public void bowl(Pin pin) {
        Frame nowFrame = tail.bowl(pin);
        if (!tail.isEqualsRound(nowFrame)) {
            tail = nowFrame;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frames frames = (Frames) o;
        return Objects.equals(head, frames.head) && Objects.equals(tail, frames.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }
}
