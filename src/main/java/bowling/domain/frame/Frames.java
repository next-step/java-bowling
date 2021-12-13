package bowling.domain.frame;

import bowling.domain.result.FrameResult;
import bowling.domain.result.FrameResults;

import java.util.ArrayList;
import java.util.List;
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

    public Frames bowl(Pin pin) {
        tail = tail.bowl(pin);
        return this;
    }

    public FrameResults createResults() {
        List<FrameResult> results = new ArrayList<>();
        Frame now = head;
        results.add(now.createResult());
        while (now.hasNext()) {
            now = now.next();
            results.add(now.createResult());
        }

        return new FrameResults(results);
    }

    public Round round() {
        return tail.round();
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
