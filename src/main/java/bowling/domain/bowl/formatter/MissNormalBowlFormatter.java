package bowling.domain.bowl.formatter;

import bowling.domain.bowl.NormalBowl;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;
import static bowling.domain.NumberOfPins.MIN_NUMBER_OF_PINS;

public class MissNormalBowlFormatter implements NormalBowlFormatter {

    public static final int SECOND_BOWL = 2;
    public static final String MISS = "{0}|{1}";

    @Override
    public boolean isSupport(NormalBowl normalBowl) {
        return normalBowl.getBowlCount() == SECOND_BOWL &&
                (normalBowl.getTotalNumberOfPins() > MIN_NUMBER_OF_PINS && normalBowl.getTotalNumberOfPins() < MAX_NUMBER_OF_PINS);
    }

    @Override
    public String format(NormalBowl normalBowl) {
        return MessageFormat.format(MISS, normalBowl.getFirstNumberOfPins(), normalBowl.getSecondNumberOfPins());
    }

}
