package bowling.domain;

import java.text.MessageFormat;

public class DefaultNormalBowlFormatter implements NormalBowlFormatter {

    public static final String DEFAULT = "{0}";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return true;
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return MessageFormat.format(DEFAULT, normalBowl.getFirstNumberOfPins());
    }

}
