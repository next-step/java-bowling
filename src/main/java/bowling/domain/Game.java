package bowling.domain;

import java.util.List;

public class Game {
    private static final int FIRST_FRAME_NUMBER = 1;
    private String userName;
    private Frames frames = new Frames();

    public Game(String userName) {
        this.userName = userName;
    }

    public Game(String userName, Frames frames) {
        this.userName = userName;
        this.frames = frames;
    }

    public NormalFrame startGame() {
        return new NormalFrame(FIRST_FRAME_NUMBER);
    }

    public void addFrame(Frame frame) {
        frames.addFrame(frame);
    }

    public String getUserName() {
        return userName;
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }
}
