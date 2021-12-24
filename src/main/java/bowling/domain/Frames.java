package bowling.domain;

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

    public boolean isEnd() {
        return current.isFinalFrame() && current.isEnd();
    }

    public void bowl(int knockedOutCount) {
        current.bowl(knockedOutCount);
    }

    public void prepareFrame() {
        if (current.isEnd()) {
            current = current.addNextFrame();
        }
    }

    public boolean isNotCurrentFrameEnd() {
        return !current.isEnd();
    }

    public List<Frame> values() {
        Frame tmp = head;
        List<Frame> list = new ArrayList<>();
        while (tmp != null) {
            list.add(tmp);
            tmp = tmp.next();
        }

        return Collections.unmodifiableList(list);
    }
}
