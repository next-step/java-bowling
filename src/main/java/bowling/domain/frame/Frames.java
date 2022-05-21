package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.init());
    }

    public void bowl(Pin no) {
        if (finished()) {
            throw new IllegalStateException();
        }
        Frame frame = last().bowl(no);
        if (frame != last()) {
            frames.add(frame);
        }
    }

    public boolean finished() {
        return last() instanceof FinalFrame && last().finished();
    }

    public int currentFrame() {
        return last().number();
    }

    private Frame last() {
        return frames.get(frames.size() - 1);
    }

    public List<Integer> scores() {
        return frames.stream()
                .filter(Frame::finished)
                .map(Frame::score)
                .filter(Score::canGetScore)
                .map(Score::getScore)
                .collect(Collectors.toList());
    }

    public List<String> expressions() {
        return frames.stream()
                .map(Frame::expression)
                .collect(Collectors.toList());
    }
}
