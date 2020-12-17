package bowling.model.frame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Frames {
    private final LinkedList<Frame> frames = Stream.of(NormalFrame.createFirstFrame())
            .collect(Collectors.toCollection(LinkedList::new));

    public void bowling(int fallenPins) {
        Frame afterBowling = lastFrame().bowling(fallenPins);

        if (afterBowling.isNewFrame()) {
            frames.add(afterBowling);
        }
    }

    public int nowFrameNumber() {
        int frameNumber = Integer.parseInt(lastFrame().frameNumber.toString());
        return isFinished() ? frameNumber + 1 : frameNumber;
    }

    public boolean isFinished() {
        return frames.size() == FrameNumber.MAX_FRAME_NUMBER && frames.getLast().isFinished();
    }

    private Frame lastFrame() {
        return frames.getLast();
    }

    public FrameResult result() {
        List<String> results = frames.stream()
                .map(Frame::toString)
                .collect(Collectors.toList());

        return FrameResult.from(results);
    }

    public FrameResult getScores() {
        Integer[] totalScore = frames.stream()
                .map(Frame::getScore)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toArray(Integer[]::new);

        Arrays.parallelPrefix(totalScore, Integer::sum);

        List<String> result = Arrays.stream(totalScore)
                .map(String::valueOf)
                .collect(Collectors.toList());

        return FrameResult.from(result);
    }
}
