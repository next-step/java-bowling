package bowling.status;

import bowling.frame.ShootScore;
import bowling.score.Score;

public interface Status {

    Status shoot(ShootScore shootScore);

    boolean isEnd();

    String board();

    Score createScore();

    int ownScore();
}
