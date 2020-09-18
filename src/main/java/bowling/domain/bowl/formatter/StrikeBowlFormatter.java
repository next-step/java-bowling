package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

public class StrikeBowlFormatter extends AbstractBowlFormatter {

    public static final String STRIKE = "X";

    @Override
    public String format(Bowl bowl) {
        return STRIKE;
    }

}
