package bowling.domain.bowl.formatter;

import bowling.domain.bowl.BowlResult;

public class NoneBowlFormatter extends AbstractBowlFormatter {

    public static final String EMPTY = "";

    @Override
    public String format(BowlResult bowlResult) {
        return EMPTY;
    }

}
