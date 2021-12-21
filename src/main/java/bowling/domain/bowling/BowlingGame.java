package bowling.domain.bowling;

import bowling.domain.pin.Pin;

import java.util.*;

public class BowlingGame {

    private final Queue<Bowling> bowlings;
    private final List<Bowling> fixedBowlings;

    public BowlingGame(List<Bowling> bowlings) {
        this(new LinkedList<>(bowlings), new ArrayList<>(bowlings));
    }

    private BowlingGame(Queue<Bowling> bowlings, List<Bowling> fixedBowlings) {
        this.bowlings = bowlings;
        this.fixedBowlings = fixedBowlings;
    }

    /**
     * @return 더 투구할 수 있는지
     */
    public boolean pitch(Pin pin) {
        Bowling bowling = currentBowling();
        int numberOfFrame = bowling.numberOfFrame();

        bowling.pitch(pin);
        if (bowling.canPitchInFrame(numberOfFrame)) {
            return true;
        }

        bowlings.add(bowlings.poll());
        return hasTurn();
    }

    private Bowling currentBowling() {
        return bowlings.peek();
    }

    private boolean hasTurn() {
        return currentBowling().canPitch();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(bowlings, that.bowlings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlings);
    }
}
