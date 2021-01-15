package bowling.domain;

public class DefaultScoreRule implements FrameScoreRule {
    @Override
    public int getAdditionCount() {
        return 0;
    }
}
