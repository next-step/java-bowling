package bowling.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum FrameScore {
    STRIKE(Scores::isStrike, "  X   |"), SPARE(Scores::isSpare, "  %s|/ |"), ONGOING(scores -> scores.getTryCount() == 1, "  %s|"), FINISH(scores -> scores.getTryCount() == 2, "  %s|%s |");

    private final Predicate<Scores> predicate;
    private final String printString;

    FrameScore(Predicate<Scores> predicate, String printString) {
        this.predicate = predicate;
        this.printString = printString;
    }

    public String getPrintString() {
        return printString;
    }

    public static FrameScore from(Scores scores) {
        return Arrays.stream(values())
                .filter(vo -> vo.predicate.test(scores))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}