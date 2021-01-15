package bowling.domain;

public class FrameScores {
    public static FrameScore immutable(FrameScore score) {
        if(score instanceof FrameScore.ImmutableScore ) return score;
        if(score.hasRemainingAddition())
            throw new IllegalStateException("점수 추가가 끝난 Score 만 immutable 로 만들 수 있습니다.");
        return new FrameScore.ImmutableScore(score.getIntValue());
    }
}
