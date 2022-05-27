package bowling.domain;

public final class HitStateJudger {

    private HitStateJudger() { }

    public static HitState judgeScore(Frame frame) {
        Score firstScore = frame.getFirstScore();
        Score secondScore = frame.getSecondScore();

        if (firstScore.isAll()) {
            return HitState.STRIKE;
        }

        if (firstScore.get() + secondScore.get() == 10) {
            return HitState.SPARE;
        }

        return HitState.NORMAL;
    }

}
