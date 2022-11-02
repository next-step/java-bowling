package bowling.domain;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

public class Bowling {

    private final Map<Position, BowlingRound> rounds = new HashMap<>();

    private Positions positions; // 꼭 Bowling 객체에서 Positions객체를 관리해야되는가? rounds map은 key값으로 Positiosn가 아닌 Position을 가지게됨

    public Bowling() {
        this(1);
    }

    public Bowling(int position) {
        Position startPosition = new Position(position);
        this.rounds.put(startPosition, new BowlingRound(position));
        this.positions = new Positions(new Position(position));
    }

    public Optional<Integer> play(int numberOfPins) {
        BowlingRound round = currentRound();
        round.addKnockDownPins(numberOfPins);
        if (round.isNextRound()) {
            Position nextPosition  = positions.next();
            BowlingRound nextRound = round.next();
            rounds.put(nextPosition, nextRound);
        }
        if (!positions.isCalculated()){
            return calculateScore();
        }
        return Optional.empty();
    }

    private Optional<Integer> calculateScore() {
        BowlingRound unCalculatedRound = rounds.get(positions.getCalculatedPosition());
        if(unCalculatedRound.isSelfCalculable()){
            //TODO Default Calculator
            return Optional.empty();
        }

        if(positions.isSpareRoundCalculable()){
            //TODO SpareRound Calculator
            return Optional.empty();
        }
        if(positions.isStrikeRoundCalculable()){
            //TODO StrikeRound Calculator
            return Optional.empty();
        }
        return Optional.empty();
    }

    public boolean isFinish() {
        return currentRound().isFinish();
    }


    public BowlingRound currentRound() {
        return rounds.get(position);
    }

    public Map<Position, BowlingRound> getRounds() {
        return rounds.entrySet()
                .stream()
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

}
