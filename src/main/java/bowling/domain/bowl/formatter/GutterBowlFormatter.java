package bowling.domain.bowl.formatter;

import bowling.domain.bowl.BowlResult;

public class GutterBowlFormatter extends AbstractBowlFormatter {

    public static final String GUTTER = "-|-";

    @Override
    public String format(BowlResult bowlResult) {
        return GUTTER;
    }

}
