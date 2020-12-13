package bowling.model.state;

import bowling.model.Score;
import bowling.model.state.finishedState.*;

public class BonusOpen extends State {
    private static final String BONUS_OPEN_ERROR = "보너스 오픈 상태는 이전 상태가 스트라이크 혹은 스페어여야합니다.";
    private State previousState;

    private BonusOpen(FinishedState previousState){
        validPreviousState(previousState);
        this.previousState = previousState;
        score = Score.from(0);
    }
    private void validPreviousState(FinishedState previousState){
        if(!(previousState instanceof Strike) && !(previousState instanceof Spare)){
            throw new IllegalArgumentException(BONUS_OPEN_ERROR);
        }
    }

    public static BonusOpen from(FinishedState previousState){
        return new BonusOpen(previousState);
    }

    @Override
    public FinishedState bowling(int fallenPin) {
        Score totalScore = score.add(fallenPin);

        if(totalScore.isMaxScore()){
            return Strike.of(score, totalScore);
        }

        if(fallenPin == 0 || totalScore.isMinScore()){
            return Gutter.of(score, totalScore);
        }

        return Miss.of(score, totalScore);
    }

    @Override
    public String toString() {
        return previousState.toString();
    }
}
