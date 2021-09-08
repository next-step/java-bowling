package step2.domain;

import java.util.List;

public interface Frame {
    void knockDown(int numOfPin);

    void isPossible();

    int score();

    boolean isFinish();

    List<Integer> scoreInfo();
}

