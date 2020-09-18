package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class ProgressNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String DEFAULT = "{0}";

    @Override
    public boolean isSupport(Bowl bowl) {
        return bowl.getBowlCount() == FIRST_BOWL &&
                bowl.getTotalNumberOfPins() < MAX_NUMBER_OF_PIN;
    }

    @Override
    public String format(Bowl bowl) {
        return MessageFormat.format(DEFAULT, bowl.getFirstNumberOfPins());
    }

}
