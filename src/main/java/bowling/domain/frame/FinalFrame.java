package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.state.*;

/**
 * Created By mand2 on 2020-12-21.
 */
public class FinalFrame extends Frame {

    public static final String ERROR_MESSAGE_FINAL_FRAME_INDEX = "마지막 프레임의 인덱스는 10이어야합니다.";

    private BowlingState state;
    private boolean endGame;

    private FinalFrame(int index) {
        super(index);
        this.state = Open.of(this);
    }

    public static FinalFrame init() {
        int index = Frames.FINAL_FRAME_INDEX;
        return of(index);
    }

    private static FinalFrame of(int index) {
        validateIndex(index);
        return new FinalFrame(index);
    }

    private static void validateIndex(int index) {
        if (Frames.FINAL_FRAME_INDEX != index) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FINAL_FRAME_INDEX);
        }
    }

    public BowlingState getState() {
        return state;
    }

    @Override
    public void pitch(Pin score) {
        if (this.state.isPlayable()) {
            this.score.pitch(score);
        }
        if (this.state.isSpare() && this.state.isFinalPlayable()) {
            this.score.bonus(score);
            this.endGame = true;
        }
        if (this.state.isStrike() && this.state.isFinalPlayable()) {
            this.score.bonus(score);
            checkStrikeBonus();
        }
    }

    private void checkStrikeBonus() {
        this.endGame = !this.score.isStrikeBonusGame();
    }

    @Override
    public boolean isPlayable() {
        checkState();

        return this.state.isPlayable() || this.state.isFinalPlayable();
    }

    @Override
    public boolean isEnd() {
        return this.endGame;
    }

    @Override
    public void checkState() {
        if (score.isStrike()) {
            this.state = Strike.of(this);
        }
        if (score.isSpare()) {
            this.state = Spare.of(this);
        }
        if (score.isMiss()) {
            this.state = Miss.of(this);
            this.endGame = true;
        }
    }

    @Override
    public int getIndex() {
        return super.getIndex();
    }

}
