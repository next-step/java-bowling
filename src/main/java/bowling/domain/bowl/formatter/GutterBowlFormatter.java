package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

public class GutterBowlFormatter extends AbstractBowlFormatter {

    public static final String GUTTER = "-|-";

    @Override
    public String format(Bowl bowl) {
        return GUTTER;
    }

}
