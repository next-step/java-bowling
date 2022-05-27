package bowling.domain;

import java.util.Optional;

interface ScoreCalculator {

    Optional<Integer> calculate(Frame frame);

}
