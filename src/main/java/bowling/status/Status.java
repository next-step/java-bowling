package bowling.status;

import bowling.frame.ShootScore;

public interface Status {

    Status shoot(ShootScore shootScore);

    boolean isEnd();
}
