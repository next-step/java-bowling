package bowling.model.frame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Frames {
    private final LinkedList<Frame> frames = Stream.of(NormalFrame.createFirstFrame())
        .collect(Collectors.toCollection(LinkedList::new));

    public void bowling(int fallenPins) {
        Frame afterBowling = lastFrame().bowling(fallenPins);

        if(afterBowling.isStartFrame()){
            frames.add(afterBowling);
        }
    }

    public int nowFrameNumber() {
        int frameNumber = Integer.parseInt(lastFrame().frameNumber.toString());
        return isFinished() ? frameNumber + 1 : frameNumber;
    }

    public boolean isFinished() {
        return lastFrame().isFinished();
    }

    private Frame lastFrame() {
        return frames.getLast();
    }

    public FrameResult result() {
        List<String> results = frames.stream()
                .filter(this::isNotStartState)
                .map(Frame::toString)
                .collect(Collectors.toList());

        return FrameResult.from(results);
    }

    public List<String> getScores(){
        return frames.stream()
                .map(Frame::getScore)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private boolean isNotStartState(Frame frame) {
        return !frame.isStartFrame();
    }
}
