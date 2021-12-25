package bowling.domain;

import bowling.annotations.ForUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final Frame head;
    private Frame current;

    public Frames() {
        head = NormalFrame.ofFirst();
        current = head;
    }

    public void prepareFrame() {
        if (current.isEnd()) {
            current = current.addNextFrame();
        }
    }

    public void bowl(int knockedOutCount) {
        current.bowl(knockedOutCount);
    }

    public boolean isEnd() {
        return current.isFinalFrame() && current.isEnd();
    }

    public boolean isCurrentFrameEnd() {
        return current.isEnd();
    }

    @ForUI
    public List<Frame> values() {
        Frame tmp = head;
        List<Frame> list = new ArrayList<>();
        while (tmp != null) {
            list.add(tmp);
            tmp = tmp.next();
        }

        return Collections.unmodifiableList(list);
    }

    @ForUI
    public Frame getCurrent() {
        return current;
    }
}
