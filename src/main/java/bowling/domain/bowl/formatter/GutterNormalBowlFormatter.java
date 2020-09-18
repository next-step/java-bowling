package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class GutterNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String GUTTER = "-|-";

    @Override
    public boolean isSupport(Bowl bowl) {
        return bowl.getBowlCount() == SECOND_BOWL &&
                bowl.getTotalNumberOfPin() == MIN_NUMBER_OF_PIN;
    }

    @Override
    public String format(Bowl bowl) {
        return GUTTER;
    }

}
