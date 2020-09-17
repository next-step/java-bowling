package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

import static bowling.domain.NumberOfPins.MIN_NUMBER_OF_PINS;

public class GutterNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String GUTTER = "-|-";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.getBowlCount() == SECOND_BOWL &&
                normalBowl.getTotalNumberOfPins() == MIN_NUMBER_OF_PINS;
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return GUTTER;
    }

}
