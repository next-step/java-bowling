package step4.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private LinkedList<Frame> result = new LinkedList<>();


    public void add(Frame frame) {
        result.add(frame);
    }

    public Frame ofFirst() {
        return result.getFirst();
    }

    public Frame ofLast() {
        return result.getLast();
    }
}
