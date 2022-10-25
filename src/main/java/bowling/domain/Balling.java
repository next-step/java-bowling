package bowling.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Balling {

    private final Map<Position,BallingRound> rounds = new HashMap<>();

    private Position currentPosition = new Position(0);

    public Balling(){
        this.rounds.put(new Position(0),new BallingRound(0));
    }

    public void play(int numberOfPins) {
        BallingRound round = currentRound();
        boolean isNextRound = round.addKnockDownPins(numberOfPins);
        if (isNextRound){
            Position position = currentPosition.next();
            currentPosition = position;
            rounds.put(position,round.next());
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
