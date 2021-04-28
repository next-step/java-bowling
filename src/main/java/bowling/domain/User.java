package bowling.domain;

import java.util.List;

public class User {

    private final String name;
    private final Frames frames;

    public User(String name) {
        this.name = name;
        this.frames = new Frames();
    }

    public String name() {
        return name;
    }

    public List<FrameStrategy> getFrameList() {
        return frames.getFrames();
    }

    public Frames getFrames() {
        return frames;
    }

    public void proceed(int frameNumber, PinNumber pinNumber) {
        frames.proceedRound(frameNumber, pinNumber);
        frames.calculateScore(frameNumber);
    }

    public boolean hasRemainTurn(int frameNumber) {
        return frames.hasRemainTurn(frameNumber);
    }
}
