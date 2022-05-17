package bowling.frame;

import bowling.status.Miss;
import bowling.status.Ready;
import bowling.status.Status;

public class LastFrame implements Frame {

    private static final int ZERO_SCORE = 0;

    private Status status;
    private ShootScore thirdShoot;

    private LastFrame() {
        status = Ready.create();
        thirdShoot = ShootScore.from(ZERO_SCORE);
    }

    public static LastFrame create() {
        return new LastFrame();
    }

    public void bonusShoot(ShootScore shootScore) {
        thirdShoot = shootScore;
    }

    public ShootScore getThirdShoot() {
        return thirdShoot;
    }

    @Override
    public void shoot(ShootScore shootScore) {
        if (isEnd()) {
            throw new IllegalArgumentException("종료된 프레임에서는 더 투구할 수 없습니다.");
        }
        status = status.shoot(shootScore);
    }

    @Override
    public boolean isEnd() {
        return notBonusStatus();
    }

    private boolean notBonusStatus() {
        return status instanceof Miss;
    }

    @Override
    public Status findMyStatus() {
        return status;
    }
}
