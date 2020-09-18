package bowling.domain.bowl;

import bowling.domain.bowl.formatter.*;
import bowling.domain.bowl.identity.*;

import java.util.Arrays;

public enum BowlStatus {

    NONE(new NoneBowlIdentity(), new NoneBowlFormatter()),
    PROGRESS(new ProgressBowlIdentity(), new ProgressBowlFormatter()),
    STRIKE(new StrikeBowlIdentity(), new StrikeBowlFormatter()),
    SPARE(new SpareBowlIdentity(), new SpareBowlFormatter()),
    MISS(new MissBowlIdentity(), new MissBowlFormatter()),
    GUTTER(new GutterBowlIdentity(), new GutterBowlFormatter())
    ;

    BowlStatus(BowlIdentity bowlIdentity, BowlFormatter bowlFormatter) {
        this.bowlIdentity = bowlIdentity;
        this.bowlFormatter = bowlFormatter;
    }

    private BowlIdentity bowlIdentity;
    private BowlFormatter bowlFormatter;

    public static BowlStatus getType(Bowl bowl) {
        return Arrays.stream(values())
                .filter(bowlStatus -> bowlStatus.identity(bowl))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean identity(Bowl bowl) {
        return bowlIdentity.identity(bowl);
    }

    public String format(Bowl bowl) {
        return bowlFormatter.format(bowl);
    }

    public boolean isCompleted() {
        return compareTo(PROGRESS) > 0 ;
    }

    public boolean isBonus() {
        return equals(STRIKE) || equals(SPARE);
    }

}
