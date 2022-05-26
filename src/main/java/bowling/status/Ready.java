package bowling.status;

import bowling.frame.ShootScore;
import bowling.score.Score;

public class Ready implements Status {

    private static final String BLANK = " ";

    private Ready() { }

    public static Ready create() {
        return new Ready();
    }

    @Override
    public Status shoot(ShootScore shootScore) {
        if (shootScore.isStrike()) {
            return Strike.create();
        }
        return FirstShoot.from(shootScore);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String board() {
        return BLANK;
    }

    @Override
    public Score createScore() {
        throw new UnsupportedOperationException("준비 상태에서는 점수를 산정할 수 없습니다.");
    }
}
