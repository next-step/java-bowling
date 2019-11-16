package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    public static final int LAST_FRAME = 10;
    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames() {
        this.frames = new ArrayList<>();
        for (int i = 0; i < LAST_FRAME; i++) {
            Frame frame = new Frame(i + 1);
            frames.add(frame);
        }
    }

    public Frame frameByIndex(int index) {
        return frames.get(index);
    }

    public List<String> getResult() {
        List<String> result = frames.stream()
                .map(Frame::getResult)
                .collect(Collectors.toList());

        if (result.size() < LAST_FRAME) {
            addEmptyResult(result);
        }
        return result;
    }

    private void addEmptyResult(List<String> result) {
        String empty = "";
        while (result.size() < LAST_FRAME) {
            result.add(empty);
        }
    }
}
