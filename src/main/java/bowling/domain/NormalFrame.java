package bowling.domain;

import bowling.state.BowlingState;
import bowling.state.Open;
import bowling.state.Strike;

/**
 * Created By mand2 on 2020-12-18.
 */
public class NormalFrame extends Frame {

    public static final int MIN_INDEX = 1;
    public static final int MAX_INDEX = 9;
    public static final String ERROR_MESSAGE_FRAME_INDEX_RANGE = "1~9 프레임 번호는 1~9사이여야 합니다.";

    private BowlingState bowlingState;

    private NormalFrame(int index) {
        super(index);
        this.bowlingState = Open.of(this);
    }

    public static NormalFrame of(int index) {
        validate(index);
        return new NormalFrame(index);
    }

    private static void validate(int index) {
        if (MIN_INDEX > index || MAX_INDEX < index) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FRAME_INDEX_RANGE);
        }
    }

    @Override
    public void firstPitch(int score) {
        this.firstPitch = Pitch.from(score);
    }

    public void secondPitch(int score) {
        if (strike()) {
            this.bowlingState = Strike.of(this);
        }
        this.secondPitch = Pitch.from(score);
    }

//    public void secondPitch(int score) {
//        super(this.index, this.firstPitch, Pitch.from(score));
//    }

    @Override
    public void isStrike() {
//        if (bowlingState.isStrike()) {
//            this.bowlingState = Strike.of(this);
//        }

    }

    public boolean strike() {
        return this.MAX_SCORE == this.getFirstPitch();
    }

    @Override
    public int getLeft() {
        return this.MAX_SCORE - (this.getFirstPitch() + this.getSecondPitch());
    }
}
