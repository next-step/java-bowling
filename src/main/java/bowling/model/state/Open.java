package bowling.model.state;

import bowling.model.Score;
import bowling.model.state.finishedState.Gutter;
import bowling.model.state.finishedState.Miss;
import bowling.model.state.finishedState.Spare;

public class Open extends State{
    private static final String OPEN_STRIKE_ERROR = "오픈 상태는 스트라이크를 가질 수 없습니다.";

    private Open(Score firstScore){
        score = firstScore;
    }

    public static Open from(Score score) {
        if(score.equals(Score.from(10))){
            throw new IllegalArgumentException(OPEN_STRIKE_ERROR);
        }
        return new Open(score);
    }

    @Override
    public State bowling(int fallenPin) {
        Score totalScore = score.add(fallenPin);

        if(totalScore.isMaxScore()){
            return Spare.of(score, totalScore);
        }

        if(fallenPin == 0 || totalScore.isMinScore()){
            return Gutter.of(score, totalScore);
        }

        return Miss.of(score, totalScore);
    }

    @Override
    public String toString() {
        if(score.equals(Score.from(0))){
            return "-";
        }

        return score.toString();
    }
}
