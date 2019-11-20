package score;

import game.GameType;

import java.util.ArrayList;
import java.util.List;

import static score.ScoreType.MAX_SCORE;

public class BonusScores implements Scores {
    private static final String BONUS_WITH_MISS_GAME = "보너스 게임은 스트라이크/스페어에서만 발생합니다";
    private static final String BONUS_OVER_THREE_TIMES = "보너스 게임 포함 총 3번의 게임만 실행 가능합니다.";
    private static final String BONUS_OVER_TWO_TIMES_WITHOUT_DOUBLE = "세번 째 보너스 게임은 더블 스트라이크 시에만 가능합니다.";
    private List<Score> bonusScores = new ArrayList<>();

    public List<Score> getScores() {
        return this.bonusScores;
    }

    @Override
    public int sumScore() {
        return bonusScores.stream()
                .map(Score::getScore)
                .reduce(Integer::sum).orElse(0);
    }

    public void addScore(GameType gameType, int score) {
        validBonusGame(gameType);
        validBonusGameCount(gameType);
        this.bonusScores.add(Score.of(score));
    }

    private void validBonusGame(GameType gameType) {
        if (gameType == GameType.MISS) {
            throw new IllegalArgumentException(BONUS_WITH_MISS_GAME);
        }
    }

    private void validBonusGameCount(GameType gameType) {
        if (bonusScores.size() == 2) {
            throw new IllegalArgumentException(BONUS_OVER_THREE_TIMES);
        }

        if (bonusScores.size() == 1 &&
                (gameType == GameType.SPARE || bonusScores.get(0) != Score.of(MAX_SCORE))) {
            throw new IllegalArgumentException(BONUS_OVER_TWO_TIMES_WITHOUT_DOUBLE);
        }
    }

    public int getNeedScoreCount() {
        if (this.bonusScores.size() == 1) {
            if (this.bonusScores.get(0).getScore() == 10) {
                return 1;
            }
            return 0;
        }
        return 0;
    }
}
