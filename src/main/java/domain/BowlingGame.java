package domain;

import View.PointResultFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {
    private static final int ZERO = 0;
    private static final int ONE = 1;
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

    public boolean playBowling(int point) {
        return normalFrameIsBowlable() ? playNormalFrame(point) : playFinalFrame(point);
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

    private boolean playNormalFrame(int point) {
        initNormalFrame();

        NormalFrame frame = normalFrames.get(lastPosition());

        if (!frame.doBowling(point)) {
            frame = frame.nextFrame();
            frame.doBowling(point);
            normalFrames.add(frame);
        }
        return true;
    }

    private boolean playFinalFrame(int point) {
        return finalFrame.doBowling(point);
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

    public int lastPosition() {
        return normalFrames.size() - ONE;
    }

    public String getResult() {
        String result = IntStream.range(ZERO, NORMAL_FRAME_COUNT)
                .mapToObj(count -> getFrameResult(count))
                .collect(Collectors.joining());
        result += finalFrame.framePoint() + "|";

        return result;
    }

    private String getFrameResult(int count) {
        final String BLANK_FRAME = "    ";
        final String SCORE_CONNECTOR = " | ";

        if (count > lastPosition()) {
            return BLANK_FRAME + SCORE_CONNECTOR;
        }
        NormalFrame frame = normalFrames.get(count);
        return frame.framePoint() + SCORE_CONNECTOR;
    }

    public String getFormattedPointResult() {
        PointResultFormatter pointResultFormatter = new PointResultFormatter();
        return pointResultFormatter.format(this);
    }

    public FinalFrame getFinalFrame() {
        return finalFrame;
    }

    public NormalFrame getNormalFrame(int frameNumber) {
        return normalFrames.get(frameNumber);
    }
}
