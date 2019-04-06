package domain.status;

public class Spare extends FrameFinished {
    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toString() {
       return "/";
    }
}