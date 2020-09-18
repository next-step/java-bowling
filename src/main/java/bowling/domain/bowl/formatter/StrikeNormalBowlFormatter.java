package bowling.domain.bowl.formatter;

import bowling.domain.bowl.Bowl;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class StrikeNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String STRIKE = "X";

    @Override
    public boolean isSupport(Bowl bowl) {
        return bowl.getBowlCount() == FIRST_BOWL &&
                bowl.getTotalNumberOfPin() == MAX_NUMBER_OF_PIN;
    }

    @Override
    public String format(Bowl bowl) {
        return STRIKE;
    }

}
