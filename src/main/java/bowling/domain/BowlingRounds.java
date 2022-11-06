package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

public class BowlingRounds {

    private final Map<Position, BowlingRound> rounds = new HashMap<>();

    private Position position;

    public BowlingRounds() {
        this.position = new Position(1);
        this.rounds.put(position, new BowlingRound(1));
    }

    public BowlingRounds(int startPosition) {
        this.position = new Position(startPosition);
        this.rounds.put(position, new BowlingRound(startPosition));
    }

    void addKnockDownPins(int numberOfPins) {
        BowlingRound currentRound = currentRound();
        currentRound.addKnockDownPins(numberOfPins);
        if (currentRound.isNextRound()) {
            Position nextPosition = position.next();
            BowlingRound nextRound = currentRound.next();
            rounds.put(nextPosition, nextRound);
            this.position = nextPosition;
        }
    }

    public BowlingRound currentRound() {
        return rounds.get(position);
    }


    public boolean isFinish() {
        return currentRound().isFinish();
    }

    public Optional<BowlingRound> findRoundByPosition(Position position) {
        BowlingRound round = rounds.get(position);
        return Optional.ofNullable(round);
    }

    public Map<Position, BowlingRound> getRounds() {
        return rounds.entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
