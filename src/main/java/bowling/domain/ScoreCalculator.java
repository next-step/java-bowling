package bowling.domain;

import java.util.OptionalInt;

interface ScoreCalculator {

    OptionalInt calculate(Frame frame);

}
