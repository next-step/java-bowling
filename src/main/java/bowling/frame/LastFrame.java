package bowling.frame;

import bowling.status.Ready;
import bowling.status.Status;

import java.util.ArrayList;
import java.util.List;

public class LastFrame implements Frame {

    private static final int ZERO_SCORE = 0;
    private static final int FIRST_SHOOT_COUNT = 1;
    private static final int SECOND_SHOOT_COUNT = 2;
    private static final int THIRD_SHOOT_COUNT = 3;

    private int shootCount = 0;

    List<ShootScore> shootScores = new ArrayList<>();

    private LastFrame() { }

    public static LastFrame create() {
        return new LastFrame();
    }

    @Override
    public void shoot(ShootScore shootScore) {
        shootCount++;
        if (isEnd()) {
            shootScores.add(ShootScore.from(ZERO_SCORE));
            return;
        }
        shootScores.add(shootScore);
    }

    @Override
    public boolean isEnd() {
        return shootCount == THIRD_SHOOT_COUNT;
    }

    @Override
    public Status findMyStatus() {
        return Ready.create();
    }
}
