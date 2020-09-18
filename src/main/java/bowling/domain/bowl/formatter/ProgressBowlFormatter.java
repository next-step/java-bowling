package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import java.text.MessageFormat;

public class ProgressBowlFormatter extends AbstractBowlFormatter {

    public static final String DEFAULT = "{0}";
    @Override
    public String format(Bowl bowl) {
        return MessageFormat.format(DEFAULT, bowl.getFirstNumberOfPins());
    }

}
