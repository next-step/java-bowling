package bowling.domain;

public class Frames {

    protected static final int BOWLING_NORMAL_FRAMES = 9;
    protected static final int BOWLING_FINAL_FRAMES = 1;

    private Frame head;
    private Frame current;
    private Integer size = 1;

    public Frames() {
        create();
    }

    public void throwBall(int hitCount) {
        current.shot(hitCount);

        if (current.isDone()) {
            current = current.next();
        }
    }

    public Frame current() {
        return current;
    }

    private void create() {
        Frame frame = new Frame();

        head = frame;
        current = frame;
        for (int i = 1; i < BOWLING_NORMAL_FRAMES; ++i) {
            frame = frame.createNext();
            ++size;
        }

        frame.createFinal();
        ++size;
    }

    public Frame head() {
        return head;
    }

    public boolean isEndGame() {
        return current == null;
    }

    public Integer size() {
        return size;
    }

}
