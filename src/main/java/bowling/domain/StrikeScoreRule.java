package bowling.domain;

public class StrikeScoreRule implements FrameScoreRule {
    @Override
    public int getAdditionCount() {
        return 2;
    }
}
