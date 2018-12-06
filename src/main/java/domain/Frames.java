package domain;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    List<Frame> frames = new ArrayList<>();

    public Frames() {
    }

    public Frames(List<Frame> frames) {
        this.frames.addAll(frames);
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(frames.stream().filter(x->x!=null).map(frame -> String.format("%6s"+StringUtils.VERTICAL_BAR,frame.toString())).collect(Collectors.joining()));
        IntStream.range(frames.size(), Frame.MAX_FRAME).forEach(x-> sb.append(String.format("%6s"+StringUtils.VERTICAL_BAR,"")));
        return sb.toString();
    }

    public int getSize() {
        return frames.size();
    }
}
