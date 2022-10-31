package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;

public class Bowling {

    private final Map<Position, BowlingRound> rounds = new HashMap<>();

    private Position currentPosition;

    public Bowling() {
        this(1);
    }

    public Bowling(int position) {
        Position startPosition = new Position(position);
        this.rounds.put(startPosition, new BowlingRound(position));
        this.currentPosition = startPosition;
    }

    public void play(int numberOfPins) {
        BowlingRound round = currentRound();
        round.addKnockDownPins(numberOfPins);
        if (round.isNextRound()) {
            Position nextPosition = currentPosition.next();
            BowlingRound nextRound = round.next();
            currentPosition = nextPosition;
            rounds.put(nextPosition, nextRound);
        }
    }

    public boolean isFinish() {
        return currentRound().isFinish();
    }


    public BowlingRound currentRound() {
        return rounds.get(currentPosition);
    }

    public Map<Position, BowlingRound> getRounds() {
        return rounds.entrySet()
                .stream()
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

}
