package bowling.domain;

public class Frames {

    protected static final int BOWLING_FRAMES_DEFAULT = 10;
    private Frame head;
    private Frame current;
    private Integer size = 1;

    public Frames() {
        create();
    }

    public int throwBall(int hitCount) {
        current.shot(hitCount);

        if (current.isDone()) {
            current = current.next();
            return 1;
        }

        return 0;
    }

    public Frame current() {
        return current;
    }

    private void create() {
        Frame frame = new Frame();

        head = frame;
        current = frame;
        for (int i = 1; i < BOWLING_FRAMES_DEFAULT; ++i) {
            frame = frame.createNext();
            ++size;
        }
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
