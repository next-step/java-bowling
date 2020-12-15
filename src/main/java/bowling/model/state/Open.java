package bowling.model.state;

import bowling.model.Score;
import bowling.model.state.finishedState.Miss;
import bowling.model.state.finishedState.Spare;

public class Open extends State {
    private static final String OPEN_STRIKE_ERROR = "오픈 상태는 스트라이크를 가질 수 없습니다.";

    private Open(Score firstScore) {
        score = firstScore;
    }

    public static Open from(Score score) {
        if (score.isMaxScore()) {
            throw new IllegalArgumentException(OPEN_STRIKE_ERROR);
        }
        return new Open(score);
    }

    @Override
    public State bowling(int fallenPin) {
        Score secondScore = Score.from(fallenPin);
        Score totalScore = score.add(secondScore);

        if (totalScore.isMaxScore()) {
            return Spare.of(secondScore, totalScore);
        }

        return Miss.from(secondScore);
    }

    @Override
    public String toString() {
        return score.toString();
    }
}
