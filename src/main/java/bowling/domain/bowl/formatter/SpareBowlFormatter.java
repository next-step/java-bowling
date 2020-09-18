package bowling.domain.bowl.formatter;

import bowling.domain.bowl.BowlResult;

import java.text.MessageFormat;

public class SpareBowlFormatter extends AbstractBowlFormatter {

    public static final String SPARE = "{0}|/";

    @Override
    public String format(BowlResult bowlResult) {
        return MessageFormat.format(SPARE, bowlResult.getFirstNumberOfPins());
    }

}
