package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private static final int FINAL_FRAME_NO = 10;

    private Name name;
    private Frames frames;

    public BowlingGame(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public String getName() {
        return name.getValue();
    }

    public List<String> getSwingInfo() {

        List<String> swingInfos = new ArrayList<>();

        for (int i = 0; i < frames.frameSize(); i++) {
            // TODO 로직 작성
        }

        for (int i = frames.frameSize(); i < FINAL_FRAME_NO; i++) {
            swingInfos.add("");
        }

        return swingInfos;
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
