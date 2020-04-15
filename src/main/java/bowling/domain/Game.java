package bowling.domain;

import java.util.List;

public class Game {
    private static final int FIRST_FRAME_NUMBER = 1;
    private String userName;
    private Frames frames;

    public Game(String userName) {
        this.userName = userName;
    }

    public Game(String userName, Frames frames) {
        this.userName = userName;
        this.frames = frames;
    }

    public NormalFrame startGame() {
        frames = new Frames();
        NormalFrame normalFrame = new NormalFrame(FIRST_FRAME_NUMBER);
        frames.addFrame(normalFrame);
        return normalFrame;
    }

    public void addFrame(NormalFrame frame) {
        frames.addFrame(frame);
    }

    public String getUserName() {
        return userName;
    }

    public List<NormalFrame> getFrames() {
        return frames.getFrames();
    }
}
