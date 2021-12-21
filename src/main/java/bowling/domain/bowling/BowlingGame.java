package bowling.domain.bowling;

import bowling.domain.pin.Pin;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import static java.util.Collections.unmodifiableList;

public class BowlingGame {

    private final Queue<Bowling> bowlings;
    private final List<Bowling> fixedBowlings;

    public BowlingGame(List<Bowling> bowlings) {
        this(new LinkedList<>(bowlings), bowlings);
    }

    private BowlingGame(Queue<Bowling> bowlings, List<Bowling> fixedBowlings) {
        this.bowlings = bowlings;
        this.fixedBowlings = fixedBowlings;
    }

    /**
     * @return 남은 차례가 있는지
     */
    public boolean pitch(Pin pin) {
        Bowling bowling = currentBowling();
        int numberOfFrame = bowling.numberOfFrame();

        bowling.pitch(pin);
        if (bowling.canPitchInFrame(numberOfFrame)) {
            return true;
        }

        changeTurn();
        return hasRemainingPitch();
    }

    private Bowling currentBowling() {
        return bowlings.peek();
    }

    private void changeTurn() {
        bowlings.add(bowlings.poll());
    }

    private boolean hasRemainingPitch() {
        return currentBowling().canPitch();
    }

    public String currentNameOfParticipant() {
        return currentBowling().nameOfParticipant();
    }

    public List<Bowling> bowlings() {
        return unmodifiableList(fixedBowlings);
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
