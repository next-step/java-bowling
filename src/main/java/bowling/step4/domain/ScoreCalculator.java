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

    private final Map<Integer, ScoreDto> scoreDtoList = new HashMap<>();
    private final Frames frames;

    public ScoreCalculator(Frames frames) {
        this.frames = frames;
    }

    public List<ScoreDto> calculate() {
        frames.frameMap().keySet().stream().filter(it->getFrame(it).pitches().getSize()>0).forEach(this::addFrameScore);
        return new ArrayList<>(scoreDtoList.values());
    }

    private void addFrameScore(int frameNum) {
        if (getFrame(frameNum).isEndedFrame() && getFrame(frameNum).isFinalFrame()) {
            addPoint(frameNum, 0);
            return;
        }

        if (getFrame(frameNum).pitches().hasSpare() && isSpareBonusEnd(frameNum)) {
            addPoint(frameNum, getSpareBonus(frameNum));
            return;
        }

        if (getFrame(frameNum).pitches().hasStrike() && isStrikeBonusEnd(frameNum)) {
            addPoint(frameNum, getStrikeBonus(frameNum));
            return;
        }

        if (getFrame(frameNum).isEndedFrame() && !getFrame(frameNum).pitches().hasStrike() && !getFrame(frameNum).pitches().hasSpare()) {
            addPoint(frameNum, 0);
        }

    }

    public void addPoint(int index, int BonusPoint) {
        int score = BonusPoint + getFrame(index).pitches().sum() + getPreviousScore(index);
        this.scoreDtoList.put(index, new ScoreDto(score));
    }

    private int getSpareBonus(int spareNum) {
        return getFrame(spareNum + 1).pitches().firstPitch();
    }

    private int getStrikeBonus(int strikeNum) {
        int min = IntStream.rangeClosed(strikeNum + 1, strikeNum + 2).filter(it -> !getFrame(it).pitches().hasStrike()).min().orElse(strikeNum + 1);
        return IntStream.rangeClosed(strikeNum + 1, min).map(it -> {
            int sum = getFrame(it).pitches().sum();
            return sum;
        }).sum();
    }

    private Boolean isStrikeBonusEnd(int strikeNum) {
        return (!getFrame(strikeNum + 1).pitches().hasStrike() && getFrame(strikeNum + 1).isEndedFrame()) || getFrame(strikeNum + 2).pitches().getSize() > 0;
    }

    private Boolean isSpareBonusEnd(int spareNum) {
        return getFrame(spareNum + 1).pitches().getSize() > 0;
    }

    private Frame getFrame(int index) {
        return this.frames.frameMap().get(index);
    }

    private int getPreviousScore(int index) {
        if (index < 2) {
            return 0;
        }
        return this.scoreDtoList.get(index - 1).score;
    }
}
