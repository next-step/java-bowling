package bowling.entity.score;

public abstract class Finish implements ScoreType {
    @Override
    public boolean isFrameEnd() {
        return true;
    }
}
