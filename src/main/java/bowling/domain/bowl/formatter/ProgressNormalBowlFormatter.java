package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;

public class ProgressNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String DEFAULT = "{0}";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.getBowlCount() == FIRST_BOWL &&
                normalBowl.getTotalNumberOfPins() < MAX_NUMBER_OF_PINS;
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return MessageFormat.format(DEFAULT, normalBowl.getFirstNumberOfPins());
    }

}
