package bowling.model.frame;

import bowling.model.Pins;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Frames {
    private final LinkedList<Frame> frames = Stream.of(NormalFrame.createFirstFrame())
            .collect(Collectors.toCollection(LinkedList::new));

    public void bowling(Pins fallenPins) {
        Frame afterBowling = lastFrame().bowling(fallenPins);

        if (afterBowling.isNewFrame()) {
            frames.add(afterBowling);
        }
    }

    public boolean isNewFrame(){
        return frames.getLast().isNewFrame();
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
        Integer[] frameScores = getFrameScores();

        Arrays.parallelPrefix(frameScores, Integer::sum);

        List<String> result = Arrays.stream(frameScores)
                .map(String::valueOf)
                .collect(Collectors.toList());

        return FrameResult.from(result);
    }

    private Integer[] getFrameScores(){
        return frames.stream()
                .map(Frame::getScore)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toArray(Integer[]::new);
    }
}
