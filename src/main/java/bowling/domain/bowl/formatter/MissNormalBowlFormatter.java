package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;
import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class MissNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String MISS = "{0}|{1}";

    @Override
    public boolean isSupport(Bowl bowl) {
        return bowl.getBowlCount() == SECOND_BOWL &&
                (bowl.getTotalNumberOfPins() > MIN_NUMBER_OF_PIN && bowl.getTotalNumberOfPins() < MAX_NUMBER_OF_PIN);
    }

    @Override
    public String format(Bowl bowl) {
        return MessageFormat.format(MISS, bowl.getFirstNumberOfPins(), bowl.getSecondNumberOfPins());
    }

}
