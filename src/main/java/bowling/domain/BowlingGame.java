package bowling.domain;

import java.util.List;

public class BowlingGame {

    private static final int FINAL_FRAME_NO = 10;

    private final Name name;
    private final Frames frames;

    public BowlingGame(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public String getName() {
        return name.getValue();
    }

    public List<String> getSwingInfo() {
        return frames.getSwingHistory();
    }

    public void swing(int score) {
        // TODO 로직 작성
    }

    public int currentRound() {
        return frames.getRound();
    }

    public boolean isBowlingGameRun() {
        return currentRound() <= FINAL_FRAME_NO;
    }
}
