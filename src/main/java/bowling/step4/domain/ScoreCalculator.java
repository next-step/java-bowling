package bowling.step4.domain;

import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.frame.Frames;
import bowling.step4.dto.ScoreDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCalculator {

    private final Map<Integer, ScoreDto> scoreDtoList = new HashMap<>();
    private final Frames frames;

    public ScoreCalculator(Frames frames) {
        this.frames = frames;
    }

    public List<ScoreDto> calculate() {
        long isEndedFrame = frames.frameMap().keySet().stream().filter(frames::isEndedFrame).peek(this::addFrameScore).count();

        int nextFrameNum = (int) isEndedFrame + 1;
        if (nextFrameNum < 10 && frames.frameMap().get(nextFrameNum).pitches().value().size() > 0) {
            calculatePrevious(nextFrameNum);
        }

        return new ArrayList<>(scoreDtoList.values());
    }

    private void calculatePrevious(int frameNum) {
        if (frameNum > 1 && getFrame(frameNum - 1).pitches().hasSpare()) {
            addPoint(frameNum - 1, getFrame(frameNum).firstPitch());
        }

        if (frameNum > 2 && getFrame(frameNum - 2).pitches().hasStrike()) {
            addPoint(frameNum - 2, getFrame(frameNum - 1).pitches().sum());
        }
    }

    private int getPreviousScore(int index) {
        if (index - 1 < 1) {
            return 0;
        }
        return this.scoreDtoList.get(index - 1).score;
    }

    private void addFrameScore(int frameNum) {
        calculatePrevious(frameNum);
        if (frameNum > 2 && getFrame(frameNum - 1).pitches().hasStrike()) {
            checkDoubleStrike(frameNum);
        }
        if (frameNum > 1 && getFrame(frameNum - 1).pitches().hasSpare() && getFrame(frameNum).pitches().hasStrike()) {
            addPoint(frameNum - 1, 10);
        }

        if (frameNum == 2 && !getFrame(frameNum).pitches().hasStrike() && getFrame(frameNum - 1).pitches().hasStrike()) {
            addPoint(frameNum - 1, getStrikeBonusPoint(frameNum));
        }
        if (getFrame(frameNum).isFinalFrame() || (!getFrame(frameNum).pitches().hasStrike() && !getFrame(frameNum).pitches().hasSpare())) {
            addPoint(frameNum, 0);
        }
    }

    private void checkDoubleStrike(int frameNum) {
        if (getFrame(frameNum).pitches().hasStrike() && getFrame(frameNum - 2).pitches().hasStrike()) {
            addPoint(frameNum - 2, 20);
        }

        if (!getFrame(frameNum).pitches().hasStrike() || getFrame(frameNum).isFinalFrame()) {
            addPoint(frameNum - 1, getStrikeBonusPoint(frameNum));
        }
    }

    private int getStrikeBonusPoint(int frameNum) {
        if (getFrame(frameNum).isFinalFrame()) {
            return getFrame(frameNum).firstPitch() + getFrame(frameNum).secondPitch();
        }
        return getFrame(frameNum).pitches().sum();
    }

    public void addPoint(int index, int BonusPoint) {
        int score = BonusPoint + getFrame(index).pitches().sum() + getPreviousScore(index);
        this.scoreDtoList.put(index, new ScoreDto(score));
    }

    private Frame getFrame(int index) {
        return this.frames.frameMap().get(index);
    }
}
