package bowling.model.state;

import bowling.model.Score;
import bowling.model.state.finishedState.*;

public class BonusOpen extends State {
    private static final String BONUS_OPEN_ERROR = "보너스 오픈 상태는 이전 상태가 스트라이크 혹은 스페어여야합니다.";
    private FinishedState state;

    private BonusOpen(FinishedState previousState) {
        validState(previousState);
        this.state = previousState;
    }

    private void validState(FinishedState state) {
        if (!state.isMaxScore()) {
            throw new IllegalArgumentException(BONUS_OPEN_ERROR);
        }
    }

    public static BonusOpen from(FinishedState previousState) {
        return new BonusOpen(previousState);
    }

    @Override
    public State bowling(int fallenPin) {
        Score bonusScore = Score.from(fallenPin);

        if (bonusScore.isMaxScore()) {
            return Strike.from(bonusScore);
        }

        return Miss.from(bonusScore);
    }

    @Override
    public int score(){
        return state.score
                .getScore();
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
