package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import java.text.MessageFormat;

public class SpareBowlFormatter extends AbstractBowlFormatter {

    public static final String SPARE = "{0}|/";

    @Override
    public String format(Bowl bowl) {
        return MessageFormat.format(SPARE, bowl.getFirstNumberOfPins());
    }

}
