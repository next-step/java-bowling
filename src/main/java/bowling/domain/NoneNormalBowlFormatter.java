package bowling.domain;

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
