package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class GutterNormalBowlFormatter extends AbstractNormalBowlFormatter {

    public static final String GUTTER = "-|-";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.getBowlCount() == SECOND_BOWL &&
                normalBowl.getTotalNumberOfPins() == MIN_NUMBER_OF_PIN;
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return GUTTER;
    }

}
