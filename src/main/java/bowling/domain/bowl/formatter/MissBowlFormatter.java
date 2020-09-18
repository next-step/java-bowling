package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import java.text.MessageFormat;

public class MissBowlFormatter extends AbstractBowlFormatter {

    public static final String MISS = "{0}|{1}";

    @Override
    public String format(Bowl bowl) {
        return MessageFormat.format(MISS, bowl.getFirstNumberOfPins(), bowl.getSecondNumberOfPins());
    }

}
