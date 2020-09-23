package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int FINAL_FRAME_NO = 10;
    private static final String EMPTY_HISTORY = "";

    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
        frames.add(FrameFactory.first());
    }

    public int getRound() {
        return frames.size();
    }

    public void swing(int score) {

        Frame frame = lastFrame();
        frame.swing(score);

        if (frame.isEndedFrame()) {
            frames.add(FrameFactory.next(frame, frames.size()));
        }
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<String> getSwingHistory() {

        Stream<String> framesStream = frames.stream()
                                            .map(Frame::swingHistoryToString);

        Stream<String> emptyStream = Stream.generate(() -> EMPTY_HISTORY)
                                           .limit(FINAL_FRAME_NO - frames.size());

        return Stream.concat(framesStream, emptyStream)
                     .collect(toList());
    }

    public boolean isEnd() {
        return frames.size() == FINAL_FRAME_NO && lastFrame().isEndedFrame();
    }
}
