package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Balling {

    private final Map<Position, BallingRound> rounds = new HashMap<>();

    private Position currentPosition;

    public Balling() {
        Position startPosition = new Position(1);
        this.currentPosition = startPosition;
        this.rounds.put(startPosition, new BallingRound(1));
    }

    public void play(int numberOfPins) {
        BallingRound round = currentRound();
        boolean isNextRound = round.addKnockDownPins(numberOfPins);
        if (isNextRound) {
            Position position = currentPosition.next();
            currentPosition = position;
            rounds.put(position, round.next());
        }
    }

    public boolean isFinish() {
        return currentRound().isFinish();
    }


    public BallingRound currentRound() {
        return rounds.get(currentPosition);
    }

    public Map<Position, BallingRound> getRounds() {
        return rounds;
    }

}
