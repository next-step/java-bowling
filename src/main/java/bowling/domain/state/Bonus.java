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
    public Score score() {
        return null;
    }
}
