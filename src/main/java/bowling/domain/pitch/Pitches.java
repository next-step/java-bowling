package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pitches {

    private static final int MAX_PINS = 10;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int MINIMUM_FINAL_SIZE = 2;

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
        int secondPins = FIRST_INDEX;
        if (this.size() >= MINIMUM_FINAL_SIZE) {
            secondPins = this.secondPitch().pins();
        }
        return this.size() >= MINIMUM_FINAL_SIZE && Math.addExact(firstPins, secondPins) == MAX_PINS;
    }

    public int totalPins() {
        return this.pitches.stream()
                .mapToInt(Pitch::pins)
                .sum();
    }

    public Pitch bonusPitch() {
        int lastIndex = this.size() - SECOND_INDEX;
        return this.pitch(lastIndex);
    }

    private Pitch pitch(int index) {
        return this.pitches.get(index);
    }

    private Pitch firstPitch() {
        return this.pitch(FIRST_INDEX);
    }

    private Pitch secondPitch() {
        return this.pitch(SECOND_INDEX);
    }

    public String currentScore() {
        if (this.isStrike()) {
            return "X";
        }

        if (this.isSpare()) {
            return this.firstPitch().pins() + "|/";
        }

        return this.pitches
                .stream()
                .map(pitch -> String.valueOf(pitch.pins()))
                .collect(Collectors.joining("|"))
                .replace("0|0", "-");
    }
}
