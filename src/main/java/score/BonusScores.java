package score;

import game.GameType;

import java.util.ArrayList;
import java.util.List;

import static score.Score.MAX_SCORE;

public class BonusScores implements Scores {
    private static final String BONUS_WITH_MISS_GAME = "보너스 게임은 스트라이크/스페어에서만 발생합니다";
    private static final String BONUS_OVER_THREE_TIMES = "보너스 게임 포함 총 3번의 게임만 실행 가능합니다.";
    private static final String BONUS_OVER_TWO_TIMES_WITHOUT_DOUBLE = "세번 째 보너스 게임은 더블 스트라이크 시에만 가능합니다.";
    private List<Score> scores = new ArrayList<>();

    public List<Score> getScores() {
        return this.scores;
    }

    public void addScore(GameType gameType, int score) {
        if (gameType == GameType.MISS) {
            throw new IllegalArgumentException(BONUS_WITH_MISS_GAME);
        }
        if ((gameType == GameType.SPARE && scores.size() == 1)
                || scores.size() == 2) {
            throw new IllegalArgumentException(BONUS_OVER_THREE_TIMES);
        }
        if (scores.size() == 1 && scores.get(0) != Score.of(MAX_SCORE)) {
            throw new IllegalArgumentException(BONUS_OVER_TWO_TIMES_WITHOUT_DOUBLE);
        }
        this.scores.add(Score.of(score));
    }
}
