package bowling.domain.state;

import java.util.OptionalInt;

interface ScoreCalculator {

    OptionalInt calculate(State state);

}
