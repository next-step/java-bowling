package bowling.domain.bowl;

import bowling.domain.bowl.formatter.*;

import java.util.Arrays;

public enum NormalBowlResult {

    NONE(new NoneNormalBowlFormatter()),
    PROGRESS(new ProgressNormalBowlFormatter()),
    STRIKE(new StrikeNormalBowlFormatter()),
    SPARE(new SpareNormalBowlFormatter()),
    MISS(new MissNormalBowlFormatter()),
    GUTTER(new GutterNormalBowlFormatter())
    ;

    NormalBowlResult(NormalBowlFormatter normalBowlFormatter) {
        this.normalBowlFormatter = normalBowlFormatter;
    }

    private NormalBowlFormatter normalBowlFormatter;

    public static NormalBowlResult getType(NormalBowl normalBowl) {
        return Arrays.stream(values())
                .filter(bowlResult -> bowlResult.normalBowlFormatter.isSupport(normalBowl))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String format(NormalBowl normalBowl) {
        return normalBowlFormatter.format(normalBowl);
    }

    public boolean isCompleted() {
        return this.compareTo(PROGRESS) > 0 ;
    }

}
