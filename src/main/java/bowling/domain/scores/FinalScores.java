package bowling.domain.scores;

import bowling.Score;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalScores extends Scores {

    private static final int MAX_SCORE_SIZE = 3;
    private static final int MAX_OF_SCORE = 30;

    public FinalScores() {
        this(new ArrayList<>());
    }

    public FinalScores(int... number) {
        this(toScore(number));
    }

    public FinalScores(Score... scores) {
        this(Arrays.asList(scores));
    }

    public FinalScores(List<Score> scores) {
        super(scores);

        if (scores.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException(String.format("점수는 %d개 이상일 수 없어요.", MAX_SCORE_SIZE));
        }

        if (sumScore() > MAX_OF_SCORE) {
            throw new IllegalArgumentException(String.format("라운드는 %d점을 넘길 수 없어요.", MAX_OF_SCORE));
        }

        if (scores.size() == MAX_SCORE_SIZE && !isRunBonus()) {
            throw new IllegalArgumentException("Strike 및 Spare를 치지 않은 경우는, 보너스 라운드가 없어요.");
        }

    }


    @Override
    public boolean isClosed() {
        if (scores.size() == MAX_SCORE_SIZE) {
            return true;
        }

        return scores.size() == 2 && !isRunBonus();
    }


    private boolean isRunBonus() {
        if (containStrike()) {
            return true;
        }

        return containSpare();
    }
}
