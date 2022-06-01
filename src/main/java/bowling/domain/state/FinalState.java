package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.CannotCalculateScoreException;
import java.util.Objects;

public class FinalState implements State {
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
            throw new IllegalStateException("마지막 프레임에서 보너스투구 기회를 얻지 못하고 세번의 투구는 불가.");
        }
        extraScore = new Score(hitCount);
    }

    @Override
    public Score score() {
        Score score = firstScore.add(secondScore.get());

        if (extraScore != null) {
            return score.add(extraScore.get());
        }
        return score;
    }

    @Override
    public Score addBonus(Score score) {
        validateBonus(firstScore);
        score = score.add(firstScore.get());
        if (score.isAddedAllBonus()) {
            return score;
        }

        validateBonus(secondScore);
        return score.add(secondScore.get());
    }

    private void validateBonus(Score score) {
        if (score == null) {
            throw new CannotCalculateScoreException("아직 점수 계산 불가");
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
        if (firstScore != null) {
            return firstScore.get() == 10;
        }
        return false;
    }

    @Override
    public boolean isSpare() {
        if (isPlayedTwice()) {
            return firstScore.get() + secondScore.get() == 10;
        }
        return false;
    }

    private boolean isPlayedTwice() {
        return firstScore != null && secondScore != null;
    }

    @Override
    public String output() {
        StringBuilder sb = new StringBuilder();

        outputFirstScore(sb);
        outputSecondScore(sb);
        outputExtraScore(sb);

        return sb.toString();
    }

    private void outputFirstScore(StringBuilder sb) {
        if (isStrike()) {
            sb.append("X");
            return;
        }

        if (isSpare()) {
            sb.append(""+firstScore.get());
            return;
        }

        sb.append(" ");
    }

    private void outputSecondScore(StringBuilder sb) {
        if (isSpare()) {
            sb.append("|"+"/");
            return;
        }

        if (isStrike() && secondScore.get() == 10) {
            sb.append("|"+"X");
            return;
        }

        if (secondScore != null) {
            sb.append("|"+secondScore.get());
            return;
        }

        sb.append("  ");
    }

    private void outputExtraScore(StringBuilder sb) {
        if (extraScore == null) {
            sb.append(" ");
            return;
        }

        if (extraScore.get() == 10) {
            sb.append("X");
            return;
        }

        if (!isSpare() && secondScore.get() + extraScore.get() == 10) {
            sb.append("/");
            return;
        }

        sb.append(extraScore.get());
    }

}
