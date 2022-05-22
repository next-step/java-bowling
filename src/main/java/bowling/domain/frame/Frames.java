package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.exception.InvalidFramesException;
import bowling.exception.OutOfIndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    public static final int START_FRAME_INDEX = 0;
    public static final int FINISH_FRAME_INDEX = 10;
    private static final int NORMAL_FRAME_LAST_SIZE = 8;
    private static final int LAST_FRAME_INDEX = 9;

    private int currentIndex;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        validateFrame(frames);
        this.frames = frames;
        this.currentIndex = START_FRAME_INDEX;
    }

    public static Frames create(List<Frame> frames) {
        return new Frames(frames);
    }

    private void validateFrame(List<Frame> frames) {
        validateSize(frames);
        validateFrameInstance(frames);
    }

    private void validateSize(List<Frame> frames) {
        if (frames.size() != FINISH_FRAME_INDEX) {
            throw new InvalidFramesException(frames.size());
        }
    }

    private void validateFrameInstance(List<Frame> frames) {
        IntStream.range(START_FRAME_INDEX, NORMAL_FRAME_LAST_SIZE)
                .forEach(index -> validateNormalFrame(frames, index));
        validateLastFrame(frames);
    }

    private void validateLastFrame(List<Frame> frames) {
        Frame lastFrame = frames.get(LAST_FRAME_INDEX);
        if (!(lastFrame instanceof LastFrame)) {
                throw new InvalidFramesException(lastFrame, LAST_FRAME_INDEX);
        }
    }

    private void validateNormalFrame(List<Frame> frames, int index) {
        Frame normalFrame = frames.get(index);
        if (!(normalFrame instanceof NormalFrame)) {
            throw new InvalidFramesException(normalFrame, index);
        }
    }

    public boolean isRunning() {
        return currentIndex < FINISH_FRAME_INDEX;
    }

    public void pitch(Pins pins) {
        Frame currentFrame = frames.get(currentIndex);
        currentFrame.pitch(pins);
    }

    public void nextIndex() {
        Frame currentFrame = frames.get(currentIndex);
        if (currentFrame.isFrameEnd()) {
            increaseIndex();
        }
    }

    private void increaseIndex() {
        int nextIndex = currentIndex + 1;
        if (nextIndex > FINISH_FRAME_INDEX) {
            throw new OutOfIndexException(nextIndex);
        }

        this.currentIndex += 1;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getCurrentRound() {
        return currentIndex + 1;
    }

    public List<Integer> sumScores() {
        List<Integer> sumScores = new ArrayList<>();
        int sum = 0;

        for (Score score : scores()) {
            if(score.isUnavailable()) {
                sumScores.add(score.score());
                continue;
            }

            sum += score.score();
            sumScores.add(sum);
        }

        return sumScores;
    }

    private List<Score> scores() {
        return frames.stream()
                .filter(Frame::isFrameEnd)
                .map(this::calculateScore)
                .collect(Collectors.toList());
    }

    private Score calculateScore(Frame frame) {
        Frame nowFrame = frame;
        int nowIndex = frames.indexOf(nowFrame);
        Score score = nowFrame.score();

        while (!score.finishCalculation()) {
            nowIndex = nextFrameIndex(nowIndex);
            nowFrame = frames.get(nowIndex);
            score = nowFrame.calculateAdditionalScore(score);
        }

        return score;
    }

    private int nextFrameIndex(int currentIndex) {
        int nextIndex = currentIndex + 1;
        if (nextIndex == FINISH_FRAME_INDEX) {
            return LAST_FRAME_INDEX;
        }
        return nextIndex;
    }

}
