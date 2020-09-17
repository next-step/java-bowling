package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

public class NoneNormalBowlFormatter implements NormalBowlFormatter {

    public static final String EMPTY = "";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.isNone();
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return EMPTY;
    }

}
