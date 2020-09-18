package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;
import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class MissNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String MISS = "{0}|{1}";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.getBowlCount() == SECOND_BOWL &&
                (normalBowl.getTotalNumberOfPins() > MIN_NUMBER_OF_PIN && normalBowl.getTotalNumberOfPins() < MAX_NUMBER_OF_PIN);
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return MessageFormat.format(MISS, normalBowl.getFirstNumberOfPins(), normalBowl.getSecondNumberOfPins());
    }

}
