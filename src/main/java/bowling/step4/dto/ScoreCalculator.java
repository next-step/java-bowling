package bowling.step4.dto;

import bowling.step4.domain.Frame.Frame;
import bowling.step4.domain.Frame.Frames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCalculator {

    private static final int START_FRAME_INDEX = 1;
    Map<Integer, ScoreDto> scoreDtoList = new HashMap<>();

    public List<ScoreDto> calculate(Frames frames) {
        long isEndedFrame = frames.frameMap().keySet().stream().filter(frames::isEndedFrame).peek(
                frameNum -> addFrameScore(frames, frameNum)).count();

        int nextFrameNum = (int) isEndedFrame + 1;
        if (frames.frameMap().get(nextFrameNum).pitches().value().size() > 1) {
            calculatePrevious(nextFrameNum, frames);
        }
        return new ArrayList<>(scoreDtoList.values());
    }

    private void calculatePrevious(int frameNum, Frames frames) {
        if (frameNum > 1 && getFrame(frames, frameNum - 1).pitches().hasSpare()) {
            addPoint(frameNum - 1, getFrame(frames, frameNum).firstPitch(), getPreviousScore(frameNum - 1));
        }

        if (frameNum > 2 && getFrame(frames, frameNum - 2).pitches().hasStrike()) {
            addPoint(frameNum - 2, getFrame(frames, frameNum - 1).pitches().sum(), getPreviousScore(frameNum - 2));
        }
    }

    private int getPreviousScore(int index) {
        if (index - 1 < 1) {
            return 0;
        }
        return this.scoreDtoList.get(index - 1).score;
    }

    private void addFrameScore(Frames frames, int frameNum) {
        if (frameNum > START_FRAME_INDEX + 1 && getFrame(frames, frameNum - 1).pitches().hasStrike()) {
            checkDoubleStrike(frames, frameNum);
        }
        if (frameNum > START_FRAME_INDEX && getFrame(frames, frameNum - 1).pitches().hasSpare() && getFrame(frames, frameNum).pitches().hasStrike()) {
            addPoint(frameNum - 1, 10, getPreviousScore(frameNum - 1));
        }

        if (frameNum == START_FRAME_INDEX + 1 && !getFrame(frames, frameNum).pitches().hasStrike() && getFrame(frames, frameNum - 1).pitches().hasStrike()) {
            addPoint(frameNum - 1, getStrikeBonusPoint(frames, frameNum), getPreviousScore(frameNum - 1));
        }
        if (getFrame(frames, frameNum).isFinalFrame() || (!getFrame(frames, frameNum).pitches().hasStrike() && !getFrame(frames, frameNum).pitches().hasSpare())) {
            addPoint(frameNum, 0, getPreviousScore(frameNum));
        }
    }

    private void checkDoubleStrike(Frames frames, int frameNum) {
        if (getFrame(frames, frameNum).pitches().hasStrike() && getFrame(frames, frameNum - 2).pitches().hasStrike()) {
            addPoint(frameNum - 2, 20, getPreviousScore(frameNum - 2));
        }

        if (!getFrame(frames, frameNum).pitches().hasStrike() || getFrame(frames, frameNum).isFinalFrame()) {
            addPoint(frameNum - 1, getStrikeBonusPoint(frames, frameNum), getPreviousScore(frameNum - 1));
        }
    }

    private int getStrikeBonusPoint(Frames frames, int frameNum) {
        if (getFrame(frames, frameNum).isFinalFrame()) {
            return getFrame(frames, frameNum).firstPitch() + getFrame(frames, frameNum).secondPitch();
        }
        return getFrame(frames, frameNum).pitches().sum();
    }

    public void addPoint(int index, int BonusPoint, int totalPoint) {
        int score = BonusPoint + getTotalScore() + totalPoint;
        this.scoreDtoList.put(index, new ScoreDto(score));
    }

    private int getTotalScore() {
        return this.scoreDtoList.keySet().stream().mapToInt(it -> this.scoreDtoList.get(it).score).sum();
    }


    private Frame getFrame(Frames frames, int index) {
        return frames.frameMap().get(index);
    }
}
