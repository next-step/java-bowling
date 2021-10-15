package step4.domain;

import java.util.LinkedList;

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

    public LinkedList<Frame> frames() {
        return result;
    }
}
