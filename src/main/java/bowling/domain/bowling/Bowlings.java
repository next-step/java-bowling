package bowling.domain.bowling;

import bowling.domain.pin.Pin;

import java.util.Collections;
import java.util.List;

public class Bowlings {
    private static final int MIN_MEMBER_INDEX = 0;
    private List<Bowling> bowlings;
    private int currentMemberIndex;

    public Bowlings(List<Bowling> bowlings, int currentMemberIndex) {
        this.bowlings = bowlings;
        this.currentMemberIndex = currentMemberIndex;
    }

    public static Bowlings of(List<Bowling> bowlings) {
        return new Bowlings(bowlings, MIN_MEMBER_INDEX);
    }

    public List<Bowling> getBowlings() {
        return Collections.unmodifiableList(bowlings);
    }

    public boolean isFinished() {
        return bowlings.stream()
                .allMatch(Bowling::isFinished);
    }

    public void throwCurrentMemberBall(Pin pin) {
        if (getCurrentMember().throwBall(pin)) {
            changeToNextMember();
        }
    }

    private Bowling getCurrentMember() {
        return bowlings.get(currentMemberIndex);
    }

    private void changeToNextMember() {
        currentMemberIndex = (currentMemberIndex + 1) % bowlings.size();
    }

    public String getCurrentMemberName() {
        return getCurrentMember().getName();
    }
}
