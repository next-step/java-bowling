package bowling.domain.bowl.formatter;

import bowling.domain.bowl.BowlResult;

import java.text.MessageFormat;

public class ProgressBowlFormatter extends AbstractBowlFormatter {

    public static final String DEFAULT = "{0}";

    @Override
    public String format(BowlResult bowlResult) {
        return MessageFormat.format(DEFAULT, bowlResult.getFirstNumberOfPins());
    }

}
