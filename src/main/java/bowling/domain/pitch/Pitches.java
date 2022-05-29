package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pitches {

    private static final int MAX_PINS = 10;
    private static final int ZERO = 0;
    private static final int FIRST = 1;
    private static final int SECOND = 2;

    private final List<Pitch> pitches;

    private Pitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Pitches first(int pins) {
        List<Pitch> pitches = new ArrayList<>();
        Pitch first = Pitch.of(pins);
        pitches.add(first);

        return new Pitches(pitches);
    }

    public Pitches next(int pins) {
        Pitch second = this.firstPitch().next(pins);
        this.pitches.add(second);

        return this;
    }

    public void bonus(int pins) {
        Pitch bonus = Pitch.of(pins);

        pitches.add(bonus);
    }

    public int size() {
        return this.pitches.size();
    }

    public boolean isStrikeOrSpare() {
        return this.isStrike() || this.isSpare();
    }

    public boolean isStrike() {
        return this.firstPitch().pins() == MAX_PINS;
    }

    public boolean isSpare() {
        int firstPins = this.firstPitch().pins();
        int secondPins = 0;
        if (this.size() >= SECOND) {
            secondPins = this.secondPitch().pins();
        }
        return this.size() >= SECOND && Math.addExact(firstPins, secondPins) == MAX_PINS;
    }

    public int totalPins() {
        return this.pitches.stream()
                .mapToInt(Pitch::pins)
                .sum();
    }

    private Pitch firstPitch() {
        return this.pitches.get(ZERO);
    }

    private Pitch secondPitch() {
        return this.pitches.get(FIRST);
    }

    @Override
    public String toString() {
        if (this.isStrike()) {
            return "X";
        }

        if (this.isSpare()) {
            return this.firstPitch().pins() + "|/";
        }

        return this.pitches
                .stream()
                .map(pitch -> {
                    if (pitch.pins() == ZERO) {
                        return "-";
                    }
                    return String.valueOf(pitch.pins());
                })
                .collect(Collectors.joining("|"));
    }
}
