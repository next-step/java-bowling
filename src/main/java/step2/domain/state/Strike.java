package step2.domain.state;

import step2.domain.frame.Frame;
import step2.domain.Pitch;

import java.util.List;

public class Strike extends Finished {

    public final static String SYMBOL = "X";
    private final static String DELIMITER = "|";

    private List<Pitch> pitches;

    public Strike(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public Strike() { }

    private int totalScore() {
        return pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
    }

    private boolean isNormalSize() {
        return pitches.size() == Frame.NORMAL_FRAME_SIZE;
    }

    private boolean isMaxSize() {
        return pitches.size() == Frame.MAX_SIZE;
    }

    @Override
    public String toString() {
        if (totalScore() == Pitch.MAX_SCORE*2 && isNormalSize()) {
            return SYMBOL + DELIMITER + SYMBOL;
        }

        if (totalScore() >= Pitch.MAX_SCORE*2 && isMaxSize()) {
            return SYMBOL + DELIMITER + pitches.get(1).toString() + DELIMITER + SYMBOL;
        }

        if (totalScore() == Pitch.MAX_SCORE*3) {
            return SYMBOL + DELIMITER + SYMBOL + DELIMITER + SYMBOL;
        }
        return SYMBOL;
    }

}
