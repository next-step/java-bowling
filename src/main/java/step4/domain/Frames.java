package step4.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<Frame> result = new ArrayList<>();


    public void add(Frame frame) {
        result.add(frame);
    }

    public Frame ofFirst() {
        return result.get(0);
    }
}
