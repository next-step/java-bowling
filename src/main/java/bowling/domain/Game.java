package bowling.domain;

public class Game {

    private String ownerName;
    private Integer frameNumber = 1;
    private Frames frames;

    public Game(String ownerName) {
        this.ownerName = ownerName;
        this.frames = new Frames();
    }

    public Frames frames() {
        return frames;
    }

    public String name() {
        return this.ownerName;
    }

    public boolean isEndGame() {
        return frames.isEndGame();
    }

    public void throwBall(int hitCount) {
        Frame current = frames.current();
        frames.throwBall(hitCount);

        if (!current.equals(frames.current())) {
            ++frameNumber;
        }
    }

    public int frameNumber() {
        return frameNumber;
    }

}