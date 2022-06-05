package bowling.domain;

public class Score {
    public static final int MAX_BONUS_CHANCE_COUNT = 2;
    public static final int MIN_BONUS_CHANCE_COUNT = 0;
    public static final int NOT_AVAILABLE_SCORE = -1;

    private static final int STRIKE_SCORE = 10;
    private static final int MAX_SCORE = 30;
    private static final int MIN_SCORE = 0;
    private static final int ONE_BOWL_CHANCE = 1;
    private static final int INIT_BONUS_CHANCE_COUNT = -1;

    private final int score;
    private final int bonusChanceCount;

    public Score(int score, int bonusChanceCount) {
        validateScoreAndBonusChanceCount(score, bonusChanceCount);
        this.score = score;
        this.bonusChanceCount = bonusChanceCount;
    }

    private static void validateScoreAndBonusChanceCount(int score, int bonusChanceCount) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException(String.format("점수는 0 ~ 30을 벗어날 수 없습니다. 현재 점수 : %d", score));
        }
        if (bonusChanceCount < MIN_BONUS_CHANCE_COUNT || bonusChanceCount > MAX_BONUS_CHANCE_COUNT) {
            throw new IllegalArgumentException(String.format("보너스 기회는 0 ~ 2를 벗어날 수 없습니다. 현재 보너스 기회 : %d", bonusChanceCount));
        }
        if (MAX_SCORE - STRIKE_SCORE * bonusChanceCount < score) {
            throw new IllegalArgumentException(String.format("보너스 기회가 2일 경우 점수는 10 이하여야 합니다. 보너스 기회가 1일 경우 점수는 20 이하여야 합니다. 보너스 기회가 0일 경우 점수는 30 이하여야 합니다. 현재 점수 : %d, 현재 보너스 기회 : %d", score, bonusChanceCount));
        }
    }

    private Score() {
        this.score = NOT_AVAILABLE_SCORE;
        this.bonusChanceCount = INIT_BONUS_CHANCE_COUNT;
    }

    public static Score init() {
        return new Score();
    }

    public static Score strike() {
        return new Score(STRIKE_SCORE, MAX_BONUS_CHANCE_COUNT);
    }

    public static Score spare() {
        return new Score(STRIKE_SCORE, MAX_BONUS_CHANCE_COUNT - ONE_BOWL_CHANCE);
    }

    public static Score miss(int totalHitPins) {
        validateTotalHitPins(totalHitPins);
        return new Score(totalHitPins, MIN_BONUS_CHANCE_COUNT);
    }

    private static void validateTotalHitPins(int totalHitPins) {
        if (totalHitPins >= STRIKE_SCORE) {
            throw new IllegalArgumentException(String.format("Miss 점수는 10 이상일 수 없습니다. 현재 점수 : %d", totalHitPins));
        }
    }

    public Score addScore(int hitPins) {
        validateBeforeAddScore();
        return new Score(score + hitPins, bonusChanceCount - ONE_BOWL_CHANCE);
    }

    private void validateBeforeAddScore() {
        if (this.bonusChanceCount == 0) {
            throw new IllegalArgumentException("보너스 기회가 0 일 경우, 점수를 더할 수 없습니다.");
        }
    }

    public boolean isNoBonusChance() {
        return bonusChanceCount == MIN_BONUS_CHANCE_COUNT;
    }

    public boolean isNotAvailableScore() {
        return this.score == NOT_AVAILABLE_SCORE;
    }

    public boolean isNotCalculable() {
        return isNoBonusChance() || isNotAvailableScore();
    }

    public int score() {
        return this.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        if (score != score1.score) return false;
        return bonusChanceCount == score1.bonusChanceCount;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + bonusChanceCount;
        return result;
    }
}
