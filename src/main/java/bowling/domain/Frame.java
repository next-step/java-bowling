package bowling.domain;

import java.util.List;

public interface Frame {
    void addScore(Score score);

    boolean isRemainChance();

    List<Score> scores();
}
