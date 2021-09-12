package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Results {
    private final List<String> results;

    private Results(Frames frames) {
        results = frames.frames()
            .stream()
            .map(Frame::toScoreSymbol)
            .collect(Collectors.toList());
    }

    public static Results from(Frames frames) {
        return new Results(frames);
    }

    public void addFrame(Frame frame) {
        results.add(frame.toScoreSymbol());
    }

    public String lastResult() {
        return results.get(
            results.size() - 1
        );
    }

    public List<String> results() {
        return results;
    }

}
