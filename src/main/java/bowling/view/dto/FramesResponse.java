package bowling.view.dto;

import bowling.model.frame.Frame;
import bowling.model.frame.Frames;

import java.util.List;
import java.util.stream.Collectors;

public final class FramesResponse {

    private final List<String> marks;
    private final List<Integer> scores;

    private FramesResponse(List<String> marks, List<Integer> scores) {
        this.marks = marks;
        this.scores = scores;
    }

    public static FramesResponse from(Frames frames) {
        return new FramesResponse(mapToMark(frames), frames.accumulatedScores());
    }

    private static List<String> mapToMark(Frames frames) {
        return frames.list()
                .stream()
                .map(Frame::mark)
                .collect(Collectors.toList());
    }

    public List<String> getMarks() {
        return marks;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
