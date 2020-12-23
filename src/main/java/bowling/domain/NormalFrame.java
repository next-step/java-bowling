package bowling.domain;

import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    public static final int MAXIMUM_PITCH = 2;
    public static final int MAXIMUM_SCORE_CONDITION = 10;
    public static final String MAXIMUM_FRAMESCORE = "한 프레임의 점수의 총 합은 10점입니다.";

    private List<Pitch> pitches;

    private NormalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static NormalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new NormalFrame(pitches);
    }

    @Override
    public void playPitch(Pitch pitch) {
        addSecondPitch(pitch);
        addFirstPitch(pitch);
    }

    private void addFirstPitch(Pitch pitch) {
        if(pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void addSecondPitch(Pitch pitch) {
        validateFrameScore(pitch);
        if(!pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void validateFrameScore(Pitch pitch) {
        if(getFrameScore() + pitch.getScore() > MAXIMUM_SCORE_CONDITION) {
            throw new IllegalArgumentException(MAXIMUM_FRAMESCORE);
        }
    }

    @Override
    public String getFirstSymbol() {
//        Pitch pitch = pitches.get(0);
//
//        Strike strike = Strike.from(pitch);
//        Gutter gutter = Gutter.from(pitch);
//
//        if(strike.isStrike()) {
//            return strike.toString();
//        }
//
//        if(gutter.isGutter()) {
//            return gutter.toString();
//        }
//
//        return "" + pitch.getScore();
        return null;
    }

    @Override
    public String getSecondSymbol() {
//        Pitch pitch = pitches.get(1);
//        Gutter gutter = Gutter.from(pitch);
//        Spare spare = Spare.from(pitches);
//        Miss miss = Miss.from(pitches);
//
//        if(gutter.isGutter()) {
//            return gutter.toString();
//        }
//
//        if(spare.isSpare()) {
//            return spare.toString();
//        }
//
//        return miss.toString();
        return null;
    }

    @Override
    public int getPitchSize() {
        return pitches.size();
    }

    @Override
    public boolean isFinish() {
        return isScoreEqualTen() || isTwicePitch();
    }

    private boolean isScoreEqualTen() {
        return getFrameScore() == MAXIMUM_SCORE_CONDITION;
    }

    private boolean isTwicePitch() {
        return pitches.size() == MAXIMUM_PITCH;
    }

    @Override
    public boolean isSpare() {
        return isScoreEqualTen() && isTwicePitch();
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
}
