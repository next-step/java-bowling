package bowling.domain.bowl.formatter;

import bowling.domain.bowl.BowlResult;

import java.text.MessageFormat;

public class MissBowlFormatter extends AbstractBowlFormatter {

    public static final String MISS = "{0}|{1}";

    @Override
    public String format(BowlResult bowlResult) {
        return MessageFormat.format(MISS, bowlResult.getFirstNumberOfPins(), bowlResult.getSecondNumberOfPins());
    }

}
