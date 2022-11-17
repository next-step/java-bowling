package bowling.step4.domain;

import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.frame.Frames;
import bowling.step4.dto.ScoreDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ScoreCalculator {

    private final Map<Integer, ScoreDto> scoreDtoList;
    private final Frames frames;

    public ScoreCalculator(Frames frames) {
        this.scoreDtoList = new HashMap<>();
        this.frames = frames;
    }

    public List<ScoreDto> calculate() {
        frames.frameMap().keySet().stream().filter(it -> getFrame(it).pitches().getSize() > 0).forEach(this::addFrameScore);
        return new ArrayList<>(scoreDtoList.values());
    }

    private void addFrameScore(int frameNum) {
        if (getFrame(frameNum).isEndedFrame() && getFrame(frameNum).isFinalFrame()) {
            addPoint(frameNum, 0);
            return;
        }

        if (!getFrame(frameNum).isFinalFrame() && getFrame(frameNum).pitches().hasSpare() && isSpareBonusEnd(frameNum)) {
            addPoint(frameNum, getSpareBonus(frameNum));
            return;
        }

        if (!getFrame(frameNum).isFinalFrame() && getFrame(frameNum).pitches().hasStrike() && isStrikeBonusEnd(frameNum)) {
            addPoint(frameNum, getStrikeBonus(frameNum));
            return;
        }

        if (!getFrame(frameNum).isFinalFrame() && getFrame(frameNum).isEndedFrame() && !getFrame(frameNum).pitches().hasStrike() && !getFrame(frameNum).pitches().hasSpare()) {
            addPoint(frameNum, 0);
        }
    }

    public void addPoint(int frameNum, int BonusPoint) {
        int score = BonusPoint + getFrame(frameNum).pitches().sum() + getPreviousScore(frameNum);
        this.scoreDtoList.put(frameNum, new ScoreDto(score));
    }

    private int getSpareBonus(int spareNum) {
        return getFrame(spareNum + 1).pitches().firstPitch();
    }

    private int getStrikeBonus(int strikeNum) {
        int bonusCount = 2;
        if (IntStream.rangeClosed(strikeNum, strikeNum + bonusCount).filter(it -> frames.frameMap().keySet().contains(it)).anyMatch(it -> !getFrame(it).pitches().hasStrike())) {
            bonusCount = 1;
        }
        return IntStream.rangeClosed(strikeNum + 1, strikeNum + bonusCount).filter(it -> frames.frameMap().keySet().contains(it))
                .map(it -> {
                    if (strikeNum == 8 && it == 10) {
                        return getFrame(it).pitches().firstPitch();
                    }
                    if (strikeNum == 9 && it == 10) {
                        return getFrame(it).pitches().firstPitch() + getFrame(it).pitches().secondPitch();
                    }
                    return getFrame(it).pitches().sum();
                }).sum();
    }

    private Boolean isStrikeBonusEnd(int strikeNum) {
        if (strikeNum == 9) {
            return getFrame(strikeNum + 1).pitches().getSize() > 1;
        }
        return (!getFrame(strikeNum + 1).pitches().hasStrike() && getFrame(strikeNum + 1).isEndedFrame()) || getFrame(strikeNum + 2).pitches().getSize() > 0;
    }

    private Boolean isSpareBonusEnd(int spareNum) {
        return getFrame(spareNum + 1).pitches().getSize() > 0;
    }

    private Frame getFrame(int frameNum) {
        return this.frames.frameMap().get(frameNum);
    }

    private int getPreviousScore(int frameNum) {
        if (frameNum < 2) {
            return 0;
        }
        return this.scoreDtoList.get(frameNum - 1).score;
    }
}
