package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class SpareNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String SPARE = "{0}|/";

    @Override
    public boolean isSupport(Bowl bowl) {
        return bowl.getBowlCount() == SECOND_BOWL &&
                bowl.getTotalNumberOfPins() == MAX_NUMBER_OF_PIN;
    }

    @Override
    public String format(Bowl bowl) {
        return MessageFormat.format(SPARE, bowl.getFirstNumberOfPins());
    }

}
