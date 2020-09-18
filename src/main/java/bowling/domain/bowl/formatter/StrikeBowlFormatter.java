package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class StrikeBowlFormatter extends AbstractBowlFormatter {

    public static final String STRIKE = "X";

    @Override
    public String format(Bowl bowl) {
        return STRIKE;
    }

}
