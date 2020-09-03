package bowling.domain;

import java.util.List;

import bowling.domain.core.Pins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.RolledStateFactory.firstBowl;
import static bowling.domain.core.RolledStateFactory.secondBowl;

final class Rolling {
    private final Frames frames;
    private RolledResult firstBowlRolledResult;

    Rolling() {
        frames = new Frames();
    }

    void roll(int fallenPins){
        if (isStrikeByFirstBowl(fallenPins)) {
            frames.saveRolledResultAndShouldNextFrame(firstBowl(fallenPins));
            return;
        }
        if (isFirstBowlOfTheTwoStepBowlingModes()){
            firstBowlRolledResult = firstBowl(fallenPins);
            frames.saveRolledResultAndShouldNextFrame(firstBowlRolledResult);
            return;
        }
        if (isSecondBowlOfTheTwoStepBowlingModes()){
            frames.saveRolledResultAndShouldNextFrame(secondBowl(firstBowlRolledResult, fallenPins));
            firstBowlRolledResult = null;
        }
    }

    private boolean isSecondBowlOfTheTwoStepBowlingModes() {
        return null != firstBowlRolledResult && firstBowlRolledResult.isNotCompleteState();
    }

    private boolean isFirstBowlOfTheTwoStepBowlingModes() {
        return null == firstBowlRolledResult;
    }

    private boolean isStrikeByFirstBowl(int fallenPins) {
        return Pins.isStrike(fallenPins);
    }

    List<String> framesByRolledResults() {
        return frames.toRolledResults();
    }

    boolean isPlayable(){
        return frames.isNotCompleteFrames();
    }

    boolean hasNextFrameIndex(int currentFrameIndex){
        return frames.currentFrameIndex() <= currentFrameIndex;
    }
}
