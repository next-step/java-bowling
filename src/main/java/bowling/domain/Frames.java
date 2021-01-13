package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int MAX_FRAME = 10;

    private List<Frame> frameList = new ArrayList<>();
    private int totalCountOfChance = 0;

    public Frames() {
        playGame();
    }

    private void playGame() {
        Frame frame;
        for (int i = 0; i < MAX_FRAME - 1; i++) {
            frame = new Frame();

            this.totalCountOfChance = totalCountOfChance + frame.playGame();
            this.frameList.add(frame);
        }

        frame = new Frame();
        this.totalCountOfChance = totalCountOfChance + frame.playGameLast();
        frameList.add(frame);
    }

    public List<Frame> getFrameList() {
        return frameList;
    }
}