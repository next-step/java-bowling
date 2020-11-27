package bowling.domain;

import java.util.LinkedList;
import java.util.List;

class Frames {
    // NOTE: getLast 를 쓰기 위해 LinkedList 로 선언
    private final LinkedList<Frame> frames = new LinkedList<>();

    int size() {
        return frames.size();
    }

    List<Frame> subList(int from, int to) {
        return frames.subList(from, to);
    }

    void update(Rolls rolls) {
        if (isLastFinished(rolls)) {
            frames.add(new Frame(rolls.size() - 1));
        }
        frames.getLast().increaseOffset();
    }

    private boolean isLastFinished(Rolls rolls) {
        return frames.isEmpty()
                || frames.getLast()
                .frameEnum(rolls) != FrameEnum.UNFINISHED;
    }
}
