package bowling.domain;

import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalFrame implements Frame {

    public static final int MAXIMUM_SCORE_CONDITION = 10;
    public static final int NORMAL_PITCH = 2;
    public static final int MAXIMUM_PITCH = 3;
    public static final String MAXIMUM_FRAMESCORE = "한 프레임의 점수의 총 합은 10점입니다.";

    private List<Pitch> pitches;

    private FinalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static FinalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new FinalFrame(pitches);
    }

    public void playPitch(Pitch pitch) {
        addRemainPitch(pitch);
        addFirstPitch(pitch);
    }

    private void addFirstPitch(Pitch pitch) {
        if(pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void addRemainPitch(Pitch pitch) {
        validateFrameScore(pitch);
        if(!pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void validateFrameScore(Pitch pitch) {
        if(isFirstPitch() && !isFrameScoreEqualTen() && isExceedMaxScore(pitch)) {
            throw new IllegalArgumentException(MAXIMUM_FRAMESCORE);
        }
    }

    private boolean isFirstPitch() {
        return getPitchSize() == 1;
    }

    private boolean isSecondPitch() {
        return getPitchSize() == NORMAL_PITCH;
    }

    private boolean isThirdPitch() {
        return getPitchSize() == MAXIMUM_PITCH;
    }

    private boolean isFrameScoreEqualTen() {
        return getFrameScore() == MAXIMUM_SCORE_CONDITION;
    }

    private boolean isExceedMaxScore(Pitch pitch) {
        return getFrameScore() + pitch.getScore() > MAXIMUM_SCORE_CONDITION;
    }

    private boolean isUnderMaxScore() {
        return getFrameScore() < MAXIMUM_SCORE_CONDITION;
    }

    private boolean isEqualMaxScore() {
        return getFrameScore() == MAXIMUM_SCORE_CONDITION;
    }

    @Override
    public String getFirstSymbol() {
        Pitch pitch = pitches.get(0);
        Strike strike = Strike.from(pitch);
        Gutter gutter = Gutter.from(pitch);
        if(strike.isStrike()) {
            return strike.toString();
        }

        if(gutter.isGutter()) {
            return gutter.toString();
        }

        return "" + pitch.getScore();
    }

    @Override
    public String getSecondSymbol() {
        Pitch pitch1 = pitches.get(0);
        Pitch pitch2 = pitches.get(1);
        Strike strike1 = Strike.from(pitch1);
        Strike strike2 = Strike.from(pitch2);
        Gutter gutter = Gutter.from(pitch2);
        List<Pitch> newPitches = Arrays.asList(pitch1, pitch2);
        Spare spare = Spare.from(newPitches);
        Miss miss = Miss.from(newPitches);

        if(strike1.isStrike() && strike2.isStrike()) {
            return strike2.toString();
        }

        if(gutter.isGutter()) {
            return gutter.toString();
        }

        if(spare.isSpare()) {
            return spare.toString();
        }

        if(miss.isMiss()) {
            return miss.toString();
        }

        return "" + pitch2.getScore();
    }

    public String getThirdSymbol() {
        Pitch pitch1 = pitches.get(1);
        Pitch pitch2 = pitches.get(2);
        Strike strike1 = Strike.from(pitch1);
        Strike strike2 = Strike.from(pitch2);
        Gutter gutter = Gutter.from(pitch2);
        List<Pitch> newPitches = Arrays.asList(pitch1, pitch2);
        Spare spare = Spare.from(newPitches);
        Miss miss = Miss.from(newPitches);

        if(strike1.isStrike() && strike2.isStrike()) {
            return strike2.toString();
        }

        if(gutter.isGutter()) {
            return gutter.toString();
        }

        if(spare.isSpare()) {
            return spare.toString();
        }

        if(miss.isMiss()) {
            return miss.toString();
        }

        return "" + pitch2.getScore();
    }

    @Override
    public int getPitchSize() {
        return pitches.size();
    }

    @Override
    public boolean isFinish() {
        return (isUnderMaxScore() && isSecondPitch()) || isThirdPitch();
    }

    @Override
    public boolean isSpare() {
        return isEqualMaxScore() && isSecondPitch();
    }

    @Override
    public int getFrameScore() {
        return pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
    }

    @Override
    public int getFirstScore() {
        return pitches.get(0).getScore();
    }

    @Override
    public int getSecondScore() {
        return pitches.get(1).getScore();
    }

    public int getThirdScore() {
        return pitches.get(2).getScore();
    }
}