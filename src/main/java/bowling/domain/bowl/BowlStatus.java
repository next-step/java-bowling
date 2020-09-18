package bowling.domain.bowl;

import bowling.domain.bowl.formatter.*;

import java.util.Arrays;

public enum BowlStatus {

    NONE(new NoneNormalBowlFormatter()),
    PROGRESS(new ProgressNormalBowlFormatter()),
    STRIKE(new StrikeNormalBowlFormatter()),
    SPARE(new SpareNormalBowlFormatter()),
    MISS(new MissNormalBowlFormatter()),
    GUTTER(new GutterNormalBowlFormatter())
    ;

    BowlStatus(NormalBowlFormatter normalBowlFormatter) {
        this.normalBowlFormatter = normalBowlFormatter;
    }

    private NormalBowlFormatter normalBowlFormatter;

    public static BowlStatus getType(Bowl bowl) {
        return Arrays.stream(values())
                .filter(bowlStatus -> bowlStatus.isSupport(bowl))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isSupport(Bowl bowl) {
        return normalBowlFormatter.isSupport(bowl);
    }

    public String format(Bowl bowl) {
        return normalBowlFormatter.format(bowl);
    }

    public boolean isCompleted() {
        return compareTo(PROGRESS) > 0 ;
    }

    public boolean isBonus() {
        return equals(STRIKE) || equals(SPARE);
    }

}
