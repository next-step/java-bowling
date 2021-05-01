package bowling.entity.score;

public abstract class OnGoing implements ScoreType {
    @Override
    public boolean isFrameEnd() {
        return false;
    }
}
