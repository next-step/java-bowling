package bowling.model;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Bowlings {

    private final List<Bowling> bowlings;

    private Bowlings(List<Bowling> bowlings) {
        Assert.notEmpty(bowlings, "bowlings must not be empty");
        this.bowlings = new ArrayList<>(bowlings);
    }

    public static Bowlings fromNames(List<String> participantNames) {
        Assert.notEmpty(participantNames, "participantNames must not be empty");
        return from(participantNames.stream()
                .map(name -> Bowling.from(Participant.from(name)))
                .collect(Collectors.toList()));
    }

    static Bowlings from(List<Bowling> bowlings) {
        return new Bowlings(bowlings);
    }

    public boolean isNotFinished() {
        return !isFinished();
    }

    public Participant nextParticipant() {
        return nextBowling().participant();
    }

    public void pitch(Pins pins) {
        validateFinished();
        Bowling bowling = nextBowling();
        int index = this.bowlings.indexOf(bowling);
        this.bowlings.remove(bowling);
        this.bowlings.add(index, bowling.pitch(pins));
    }

    public List<Bowling> list() {
        return Collections.unmodifiableList(bowlings);
    }

    private void validateFinished() {
        if (isFinished()) {
            throw new IllegalStateException("bowlings(%s) is already finished");
        }
    }

    private Bowling nextBowling() {
        return bowlings.stream()
                .filter(bowling -> !bowling.isFinished())
                .reduce((bowling, bowling2) -> {
                    if (bowling.isNextFrameNumberGreaterThan(bowling2)) {
                        return bowling2;
                    }
                    return bowling;
                }).orElseThrow(() -> new IllegalStateException(String.format("bowlings(%s) does not have next bowling", this)));
    }

    private boolean isFinished() {
        return bowlings.stream()
                .allMatch(Bowling::isFinished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bowlings bowlings1 = (Bowlings) o;
        return Objects.equals(bowlings, bowlings1.bowlings);
    }

    @Override
    public String toString() {
        return "Bowlings{" +
                "bowlings=" + bowlings +
                '}';
    }
}
