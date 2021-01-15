package bowling.domain;

public class SpareScoreRule implements FrameScoreRule {
    @Override
    public int getAdditionCount() {
        return 1;
    }
}
