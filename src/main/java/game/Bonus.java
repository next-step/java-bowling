package game;

import java.util.ArrayList;
import java.util.List;

public class Bonus {
    private final GameType gameType;
    private List<Score> bonusScore;

    private Bonus(GameType gameType) {
        this.gameType = gameType;
        this.bonusScore = new ArrayList<>();
    }

    public List<Score> getBonusScore() {
        return bonusScore;
    }

    public static Bonus of(GameType gameType, int score) {
        Bonus bonus = new Bonus(gameType);
        bonus.bonusScore.add(Score.of(score));
        return bonus;
    }

    public void addBonus(int score) {
        if (this.gameType == GameType.SPARE || bonusScore.size() == 2) {
            throw new IllegalArgumentException("보너스 게임 포함 총 3번의 게임만 실행 가능합니다.");
        }
        if (bonusScore.get(0) != Score.of(10)) {
            throw new IllegalArgumentException("세번 째 보너스 게임은 더블 스트라이크 시에만 가능합니다.");
        }
        this.bonusScore.add(Score.of(score));
    }
}
