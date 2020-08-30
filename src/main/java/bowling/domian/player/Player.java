package bowling.domian.player;

import bowling.domian.frame.Frames;

import java.util.List;

public class Player {
    private final Name name;
    private final Frames frames;

    private Player(Name name) {
        this.name = name;
        this.frames = Frames.of();
    }

    public static Player get(String name) {
        return new Player(Name.get(name));
    }

    public String getName() {
        return name.getName();
    }

    // TODO: 2020/08/30 리팩토링
    public List<String> getDescs() {
        return frames.getBoard().getDescs();
    }

    public List<Integer> getTotalScores() {
        return frames.getBoard().getTotalScores();
    }

    public boolean isFrameNumber(int frameNumber) {
        return frames.isFrameNumber(frameNumber);
    }

    public void bowl(int falledPinsCount) {
        frames.bowl(falledPinsCount);
    }

    public boolean isGameEnd() {
        return frames.isGameEnd();
    }
}
