package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

public class NoneNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String EMPTY = "";

    @Override
    public boolean isSupport(Bowl bowl) {
        return bowl.isNone();
    }

    @Override
    public String format(Bowl bowl) {
        return EMPTY;
    }

}
