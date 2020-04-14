package bowling.domain;

import java.util.List;

public class Game {
    private String userName;
    private Frames frames;

    public Game(String userName) {
        this.userName = userName;
    }

    public Game(String userName, Frames frames) {
        this.userName = userName;
        this.frames = frames;
    }
    public void startGame() {
        frames = new Frames();
        frames.addFrame(new NormalFrame(1));
    }

    public String getUserName() {
        return userName;
    }

    public List<NormalFrame> getFrames() {
        return frames.getFrames();
    }
}
