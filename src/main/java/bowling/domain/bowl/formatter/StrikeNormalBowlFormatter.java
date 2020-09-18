package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class StrikeNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String STRIKE = "X";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.getBowlCount() == FIRST_BOWL &&
                normalBowl.getTotalNumberOfPins() == MAX_NUMBER_OF_PIN;
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return STRIKE;
    }

}
