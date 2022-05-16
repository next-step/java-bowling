package bowling.domain.frameresult;

public class Strike implements FrameResult {

    @Override
    public int getScoreWithBonus(int bonus) {
        return 10 + bonus;
    }
}
