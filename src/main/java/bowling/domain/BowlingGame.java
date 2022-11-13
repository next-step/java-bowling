package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BowlingGame {
    private final List<Frame> frameList;

    public BowlingGame() {
        this.frameList = initFrameList();
    }

    private List<Frame> initFrameList() {
        return new ArrayList<>(List.of(new NormalFrame()));
    }

    public void play(int knockedDownPinCount) {
        getCurrentFrame().play(knockedDownPinCount);

        if (isCurrentFrameEnded() && isGamePlayable()) {
            frameList.add(getCurrentFrame().createNextFrame());
        }
    }

    private Frame getCurrentFrame() {
        return frameList.get(frameList.size() - 1);
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getRound();
    }

    private boolean isCurrentFrameEnded() {
        return getCurrentFrame().isEnd();
    }

    public boolean isGamePlayable() {
        return !(getCurrentFrame().getRound() == Frame.FINAL_FRAME_NUMBER && isCurrentFrameEnded());
    }

    public List<String> playRecords() {
        return frameList.stream()
                .filter(frame -> frame.balls.pitchedAnyBalls())
                .map(Frame::scoringText)
                .collect(toList());
    }

}
