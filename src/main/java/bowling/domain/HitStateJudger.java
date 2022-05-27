package bowling.domain;

public final class HitStateJudger {

    private HitStateJudger() { }

    public static HitState judgeStrike(Frame frame) {
        Score firstScore = frame.getFirstScoreAsOptional()
            .orElse(new Score());

        if (firstScore.isAll()) {
            return HitState.STRIKE;
        }

        return HitState.NORMAL;
    }

    public static HitState judgeHitState(Frame frame) {
        Score firstScore = frame.getFirstScoreAsOptional()
            .orElse(new Score());
        Score secondScore = frame.getSecondScoreAsOptional()
            .orElse(new Score());

        if (firstScore.isAll()) {
            return HitState.STRIKE;
        }

        if (firstScore.get() + secondScore.get() == 10) {
            return HitState.SPARE;
        }

        return HitState.NORMAL;
    }

}
