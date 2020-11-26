package bowling.domain;

import java.util.LinkedList;

class Frames {
    // NOTE: getLast 를 쓰기 위해 LinkedList 로 선언
    private final LinkedList<Frame> frames = new LinkedList<>();

    int size() {
        return frames.size();
    }

    void add(Roll roll) {
        if (isLastFinished()) {
            frames.add(new Frame());
        }
        frames.getLast().add(roll);
    }

    Frame last() {
        return frames.getLast();
    }

    private boolean isLastFinished() {
        return frames.isEmpty() || last().isFinished();
    }
}
