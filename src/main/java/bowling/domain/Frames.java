package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    List<Frame> frames = new ArrayList<>();

    public boolean add(Frame frame) {
        return frames.add(frame);
    }

    public List<String> showGameResult() {
        return frames.stream()
                .map(Frame::showResult)
                .collect(Collectors.toList());
    }

}
