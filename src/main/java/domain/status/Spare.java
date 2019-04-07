package domain.status;

public class Spare extends FrameFinished {
    static final String SPARE_DISPLAY_STRING = "/";

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toString() {
       return SPARE_DISPLAY_STRING;
    }
}