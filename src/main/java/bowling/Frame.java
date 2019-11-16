package bowling;

public class Frame {
    private static final int LAST_FRAME = 10;
    private int number;
    private Balls balls;

    public Frame(int frameNumber) {
        this.number = frameNumber;
    }

    public void addBall(int pin) {
       balls.add(pin, isLastFrame());
    }

    private boolean isLastFrame() {
        return number == LAST_FRAME;
    }

    public int getScore() {
        return balls.score();
    }
}
