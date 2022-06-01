package bowling.domain.state;

import bowling.domain.score.Score;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bonus extends State {

    private final State state;
    private final State bonusState;

    public Bonus(State state, State bonusState) {
        this.state = state;
        this.bonusState = bonusState;
    }

    @Override
    public State bonusBowling(int pins) {
        return new Bonus(this, Ready.of(pins));
    }

    @Override
    public State bowling(int pins) {
        return Ready.of(pins);
    }

    @Override
    public String symbol() {
        return Stream.of(this.state, this.bonusState)
                .map(State::symbol)
                .collect(Collectors.joining("|"));
    }

    @Override
    public int totalScore() {
        return Math.addExact(this.state.totalScore(), this.bonusState.totalScore());
    }

    @Override
    public Score calculateScore(Score before) {
        if (this.state.is(Bonus.class)) {
            return calculateScoreByBonus(before);
        }

        Score after = before.nextScore(this.state.totalScore());

        if (after.doNotCalculate()) {
            return after;
        }

        return after.nextScore(this.bonusState.totalScore());
    }

    private Score calculateScoreByBonus(Score before) {
        Bonus bonus = (Bonus) this.state;
        Score first = bonus.state.calculateScore(before);

        if (first.doNotCalculate()) {
            return first;
        }

        return first.nextScore(bonus.bonusState.totalScore());
    }
}
