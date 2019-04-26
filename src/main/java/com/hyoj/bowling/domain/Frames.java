package com.hyoj.bowling.domain;

import static java.util.stream.Collectors.joining;

import com.hyoj.bowling.console.OutputConsole;
import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final List<Frame> frames = new ArrayList<>();

    public int size() {
        return frames.size();
    }

    public boolean add(final Frame frame) {
        return frames.add(frame);
    }

    public Frame get(final int index) {
        return frames.get(index);
    }

    public String toStringForOptional() {
        return frames.stream()
            .map(Object::toString)
            .collect(joining(OutputConsole.BAR));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        frames.forEach(frame -> result.append(OutputConsole.toStringWithBar(frame.toString())));
        return result.toString();
    }
}