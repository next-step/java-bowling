package domain.status;

public class Spare extends FrameFinished {
    static final int SPARE_BONUS_COUNT = 1;
    static final String SPARE_DISPLAY_STRING = "/";

    @Override
    public int getBonusCount() {
        return SPARE_BONUS_COUNT;
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toString() {
       return SPARE_DISPLAY_STRING;
    }
}