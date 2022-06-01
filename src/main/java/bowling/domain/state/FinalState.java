package bowling.domain.state;

import static bowling.domain.ScoreSymbols.GUTTER_SYMBOL;
import static bowling.domain.ScoreSymbols.MISS_SYMBOL;
import static bowling.domain.ScoreSymbols.SCORE_GAP;
import static bowling.domain.ScoreSymbols.SCORE_SEPARATOR;
import static bowling.domain.ScoreSymbols.SPARE_SYMBOL;
import static bowling.domain.ScoreSymbols.STRIKE_SYMBOL;

import bowling.domain.Score;
import bowling.exception.CannotCalculateScoreException;
import java.util.Objects;

public class FinalState implements State {
    private static final String NO_BONUS_CHANCE = "마지막 프레임에서 보너스투구 기회를 얻지 못하고 세번의 투구는 불가.";
    private static final String CANNOT_CALCULATE_SCORE_YET = "아직 점수 계산 불가";

    private Score firstScore;
    private Score secondScore;
    private Score extraScore;

    @Override
    public State bowl(int hitCount) {
        if (firstScore == null) {
            firstScore = new Score(hitCount);
            return State.ofReady();
        }

        if (secondScore == null) {
            secondScore = new Score(hitCount);
            return State.ofReady();
        }

        checkValidShot(hitCount);
        return State.ofReady();
    }

    private void checkValidShot(int hitCount) {
        if (!isSpareOrStrike()) {
            throw new IllegalStateException(NO_BONUS_CHANCE);
        }
        extraScore = new Score(hitCount);
    }

    @Override
    public Score score() {
        Score score = firstScore.add(secondScore);

        if (Objects.nonNull(extraScore)) {
            return score.add(extraScore);
        }
        return score;
    }

    @Override
    public Score addBonus(Score score) {
        validateBonus(firstScore);
        score = score.add(firstScore);
        if (score.isAddedAllBonus()) {
            return score;
        }

        validateBonus(secondScore);
        return score.add(secondScore);
    }

    private void validateBonus(Score score) {
        if (Objects.isNull(score)) {
            throw new CannotCalculateScoreException(CANNOT_CALCULATE_SCORE_YET);
        }
    }

    @Override
    public boolean isDone() {
        return (!isSpareOrStrike() && isPlayedTwice())
            || Objects.nonNull(extraScore);
    }

    private boolean isSpareOrStrike() {
        return this.isSpare()
            || this.isStrike();
    }

    @Override
    public boolean isStrike() {
        if (Objects.nonNull(firstScore)) {
            return firstScore.isStrike();
        }
        return false;
    }

    @Override
    public boolean isSpare() {
        if (isPlayedTwice()) {
            return firstScore.isSpareWith(secondScore);
        }
        return false;
    }

    private boolean isPlayedTwice() {
        return Objects.nonNull(firstScore) && Objects.nonNull(secondScore);
    }

    private boolean isSecondStrike() {
        return Objects.nonNull(secondScore) && secondScore.isStrike();
    }

    @Override
    public String output() {
        StringBuilder scoreStringBuilder = new StringBuilder();

        outputFirstScore(scoreStringBuilder);
        outputSecondScore(scoreStringBuilder);
        outputExtraScore(scoreStringBuilder);

        return scoreStringBuilder.toString();
    }

    private void outputFirstScore(StringBuilder sb) {
        if (Objects.isNull(firstScore)) {
            sb.append(SCORE_GAP);
            return;
        }

        if (isStrike()) {
            sb.append(STRIKE_SYMBOL);
            return;
        }

        if (firstScore.isMiss()) {
            sb.append(GUTTER_SYMBOL);
            return;
        }

        sb.append(firstScore);
    }

    private void outputSecondScore(StringBuilder sb) {
        if (Objects.isNull(secondScore)) {
            sb.append(SCORE_GAP + SCORE_GAP);
            return;
        }

        if (isSpare()) {
            sb.append(SCORE_SEPARATOR + SPARE_SYMBOL);
            return;
        }

        if (isStrike() && isSecondStrike()) {
            sb.append(SCORE_SEPARATOR + STRIKE_SYMBOL);
            return;
        }

        if (secondScore.isMiss()) {
            sb.append(SCORE_SEPARATOR + MISS_SYMBOL);
            return;
        }

        sb.append(SCORE_SEPARATOR).append(secondScore);

    }

    private void outputExtraScore(StringBuilder sb) {
        if (Objects.isNull(extraScore)) {
            sb.append(SCORE_GAP);
            return;
        }

        if (extraScore.isMiss()) {
            sb.append(MISS_SYMBOL);
            return;
        }

        if (extraScore.isStrike()) {
            sb.append(STRIKE_SYMBOL);
            return;
        }

        if (!isSpare() && secondScore.isSpareWith(extraScore)) {
            sb.append(SPARE_SYMBOL);
            return;
        }

        sb.append(extraScore);
    }

}
