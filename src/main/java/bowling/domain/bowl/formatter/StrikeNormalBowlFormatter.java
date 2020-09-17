package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;

public class StrikeNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String STRIKE = "X";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.getBowlCount() == FIRST_BOWL &&
                normalBowl.getTotalNumberOfPins() == MAX_NUMBER_OF_PINS;
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return STRIKE;
    }

}
