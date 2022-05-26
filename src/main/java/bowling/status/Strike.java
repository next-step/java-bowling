package bowling.status;

import bowling.frame.ShootScore;
import bowling.score.Score;

public class Strike implements Status {

    private static final String STRIKE_SIGNATURE = "X";
    private static final int MAX_SCORE = 10;

    private Strike() { }

    public static Strike create() {
        return new Strike();
    }

    @Override
    public Status shoot(ShootScore shootScore) {
        throw new UnsupportedOperationException("마지막 프레임을 제외한 일반 프레임에서 스트라이크는 더 투구하지 않습니다");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String board() {
        return STRIKE_SIGNATURE;
    }

    @Override
    public Score createScore() {
        return Score.toStrike();
    }

    @Override
    public int ownScore() {
        return MAX_SCORE;
    }
}
