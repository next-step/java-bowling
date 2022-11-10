package bowling.domain;

public class NormalBowlings extends Bowlings {

    private static final int MAX_TOTAL = 10;

    @Override
    int getMaxTotalCont() {
        return MAX_TOTAL;
    }
}
