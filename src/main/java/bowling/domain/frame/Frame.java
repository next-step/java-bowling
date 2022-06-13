package bowling.domain.frame;

import java.util.List;

public interface Frame {
    int score();

    int validateMoveToNextIndex();

    boolean equal(int index);

    List<Integer> scores();
}
