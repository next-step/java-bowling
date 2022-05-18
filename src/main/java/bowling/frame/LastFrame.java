package bowling.frame;

import bowling.status.*;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.*;

public class LastFrame implements Frame {

    private static final int SECOND_SHOOT_COUNT = 2;

    private int shootCount = 0;

    private LinkedList<Status> statuses = new LinkedList<>();

    private LastFrame() {
        statuses.add(Ready.create());
    }

    public static LastFrame create() {
        return new LastFrame();
    }

    @Override
    public void shoot(ShootScore shootScore) {
        shootCount++;

        if (shootScore.isStrike()) {
            createStrikeStatus();
            return;
        }

        Status currentStatus = statuses.getLast();

        if (currentStatus instanceof Spare) {
            statuses.add(Ready.create().shoot(shootScore));
            return ;
        }

        statuses.removeLast();
        statuses.add(currentStatus.shoot(shootScore));
    }

    private void createStrikeStatus() {
        statuses.add(Strike.create());
        statuses.add(Ready.create());
    }

    @Override
    public boolean isEnd() {
        return shootCount == SECOND_SHOOT_COUNT && statuses.getLast() instanceof Miss;
    }

    @Override
    public Status findMyStatus() {
        return statuses.getLast();
    }

    public List<Status> findMyAllStatus() {
        return unmodifiableList(statuses);
    }
}
