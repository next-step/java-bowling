package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {
    public static final int NORMAL_FRAME_COUNT = 9;

    private List<NormalFrame> normalFrames;
    private FinalFrame finalFrame;

    public BowlingGame() {
        this.normalFrames = new ArrayList<>(NORMAL_FRAME_COUNT);
        this.finalFrame = new FinalFrame();
        init();
    }

    private void init() {
        this.normalFrames.add(new NormalFrame());
    }

    public int playBowling(int score) {
        return normalFrameIsBowlable() ? playNormalFrame(score) : playFinalFrame(score);
    }

    private boolean normalFrameIsBowlable() {
        int last = lastPosition();
        int normalFrameCount = getNormalFrameCount();

        if (normalFrameCount < NORMAL_FRAME_COUNT ||
                (normalFrameCount == NORMAL_FRAME_COUNT && normalFrames.get(last).nowBowlable())) {
            return true;
        }
        return false;
    }

    private int playNormalFrame(int score) {
        NormalFrame frame = normalFrames.get(lastPosition());

        if (!frame.doBowling(score)) {
            frame = frame.nextFrame();
            frame.doBowling(score);
            normalFrames.add(frame);
        }

        return sumScore();
    }

    private int playFinalFrame(int score) {
        finalFrame.doBowling(score);
        return finalFrame.sumScore();
    }

    public int sumScore() {
        int normalScore = normalFrames.stream()
                .mapToInt(frame -> frame.sumScore())
                .sum();
        int finalScore = finalFrame.sumScore();
        return normalScore + finalScore;
    }

    public int getNormalFrameCount() {
        return normalFrames.size();
    }

    private int lastPosition() {
        return normalFrames.size() - 1;
    }

    public String getScore() {
        final String LINE_ENTER = "\n";
        StringBuilder builder = new StringBuilder();

        normalFrames.stream()
                .forEach(frame -> builder.append(frame.getScore() + LINE_ENTER));
        builder.append(finalFrame.getScore());

        return builder.toString();
    }
}
