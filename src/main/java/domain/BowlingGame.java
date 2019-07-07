package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int FINAL_FRAME_COUNT = 1;
    public static final int NORMAL_FRAME_COUNT = 9;
    public static final int TOTAL_FRAME_COUNT = 10;
    public static final int MAX_BOWL_COUNT = 2 * 9 + 3;

    private List<NormalFrame> normalFrames;
    private FinalFrame finalFrame;

    public BowlingGame() {
        this.normalFrames = new ArrayList<>(NORMAL_FRAME_COUNT);
        this.finalFrame = new FinalFrame();
    }

    private void initNormalFrame() {
        if (normalFrames.size() == ZERO) {
            normalFrames.add(new NormalFrame(ONE));
        }
    }

    public int playBowling(int score) {
        return normalFrameIsBowlable() ? playNormalFrame(score) : playFinalFrame(score);
    }

    private boolean normalFrameIsBowlable() {
        int last = lastPosition();
        int normalFrameCount = getNextFrameNumber();

        if (normalFrameCount < TOTAL_FRAME_COUNT ||
                (normalFrameCount == NORMAL_FRAME_COUNT && normalFrames.get(last).nowBowlable())) {
            return true;
        }
        return false;
    }

    private int playNormalFrame(int score) {
        initNormalFrame();

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

    public boolean isGameOver() {
        return finalFrame.isGameOver();
    }

    public int getNextFrameNumber() {
        int last = lastPosition();
        if (last == -1) {
            return ONE;
        }
        NormalFrame frame = normalFrames.get(last);
        return frame.getNextFrameNumber();
    }

    private int lastPosition() {
        return normalFrames.size() - ONE;
    }

    public int sumScore() {
        int normalScore = normalFrames.stream()
                .mapToInt(frame -> frame.sumScore())
                .sum();
        int finalScore = finalFrame.sumScore();
        return normalScore + finalScore;
    }

    public String getResult() {
        String result = IntStream.range(0, NORMAL_FRAME_COUNT)
                .mapToObj(count -> getFrameResult(count))
                .collect(Collectors.joining());
        result += finalFrame.getResult() + "|";

        return result;
    }

    private String getFrameResult(int count) {
        final String BLANK_FRAME = "    ";
        final String SCORE_CONNECTOR = " | ";

        if(count > lastPosition()) {
            return BLANK_FRAME + SCORE_CONNECTOR;
        }
        NormalFrame frame = normalFrames.get(count);
        return frame.getResult() + SCORE_CONNECTOR;
    }
}
