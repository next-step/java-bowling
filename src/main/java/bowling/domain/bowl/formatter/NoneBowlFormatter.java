package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

public class NoneBowlFormatter extends AbstractBowlFormatter {

    public static final String EMPTY = "";

    @Override
    public String format(Bowl bowl) {
        return EMPTY;
    }

}
