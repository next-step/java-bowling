package bowling.domain.bowl.formatter;

import bowling.domain.bowl.BowlResult;

public class StrikeBowlFormatter extends AbstractBowlFormatter {

    public static final String STRIKE = "X";

    @Override
    public String format(BowlResult bowlResult) {
        return STRIKE;
    }

}
