package step2.domain;

import java.util.List;

public interface Frame {
    abstract void knockDown(int numOfPin);

    abstract void isPossible();

    abstract int score();

    abstract boolean isFinish();

    abstract List<Integer> scoreInfo();
}

