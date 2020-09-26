package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int LAST_INDEX_GAP = 1;
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
        return frames.get(frames.size() - LAST_INDEX_GAP);
    }

    public List<String> getSwingHistories() {
        return createStringList(frameStream().map(Frame::swingHistoryToString));
    }

    public List<String> getScores() {
        return createStringList(frameStream().map(Frame::getScore));
    }

    private Stream<Frame> frameStream() {
        return frames.stream().limit(FINAL_FRAME_NO);
    }

    private List<String> createStringList(Stream<String> stream) {
        
        List<String> list = stream.collect(toList());
        
        for (int i = frames.size(); i < FINAL_FRAME_NO; i++) {
            list.add(EMPTY_HISTORY);
        }
        
        return list;
    }

    public boolean isEnd() {
        return frames.size() > FINAL_FRAME_NO;
    }
}
