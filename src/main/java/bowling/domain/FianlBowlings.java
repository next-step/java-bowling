package bowling.domain;

public class FianlBowlings extends Bowlings {

    private static final int MAX_TOTAL = 30;

    @Override
    int getMaxTotalCont() {
        return MAX_TOTAL;
    }
}
