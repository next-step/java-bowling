package bowling.domain.bowling;

import bowling.domain.pin.Pin;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class BowlingGame {

    private static final int FIRST_TURN = 0;
    private static final int TURN_UNIT = 1;

    private int turn;
    private final List<Bowling> bowlings;

    public BowlingGame(List<Bowling> bowlings) {
        this(FIRST_TURN, bowlings);
    }

    public BowlingGame(int turn, List<Bowling> bowlings) {
        this.turn = turn;
        this.bowlings = bowlings;
    }

    public void pitch(Pin pin) {
        Bowling bowling = currentBowling();
        int numberOfFrame = bowling.numberOfFrame();

        bowling.pitch(pin);
        if (bowling.isFrameEnd(numberOfFrame)) {
            changeTurn();
        }
    }

    private Bowling currentBowling() {
        return bowlings.get(turn);
    }

    private void changeTurn() {
        turn = (turn + TURN_UNIT) % bowlings.size();
    }

    public boolean isGameInProgress() {
        return currentBowling().canPitch();
    }

    public String currentNameOfParticipant() {
        return currentBowling().nameOfParticipant();
    }

    public List<Bowling> bowlings() {
        return unmodifiableList(bowlings);
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
