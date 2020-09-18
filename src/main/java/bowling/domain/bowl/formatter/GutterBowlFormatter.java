package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class GutterBowlFormatter extends AbstractBowlFormatter {

    public static final String GUTTER = "-|-";

    @Override
    public String format(Bowl bowl) {
        return GUTTER;
    }

}
