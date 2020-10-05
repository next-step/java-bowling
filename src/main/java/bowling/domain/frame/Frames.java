package bowling.domain.frame;

import bowling.domain.ScoreUpdater;
import bowling.domain.Swing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int LAST_INDEX_GAP = 1;
    private static final int FINAL_FRAME_NO = 10;
    private static final String BLANK = "";

    private final List<Frame> frames;
    private final ScoreUpdater updater;

    public Frames() {
        frames = new ArrayList<>();
        frames.add(FrameFactory.first());
        updater = new ScoreUpdater();
    }

    public int getRound() {
        return frames.size();
    }

    public void swing(int score) {

        Frame frame = lastFrame();
        frame.swing(score);
        updateFrameScore(score);
        checkFrameNeedUpdate(frame);

        if (frame.isEndedFrame() && frames.size() <= FINAL_FRAME_NO) {
            frames.add(FrameFactory.next(frames.size()));
        }
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - LAST_INDEX_GAP);
    }

    private void updateFrameScore(int score) {
        updater.update(score);
    }

    private void checkFrameNeedUpdate(Frame frame) {
        updater.checkFrameNeedUpdate(frame);
    }

    public List<String> getSwingRecords() {
        return createFullFrameList(frameStream().map(Frame::swingRecordsToString)
                                                .collect(toList()));
    }

    public List<String> getScores() {

        int sum = 0;
        List<String> scores = new ArrayList<>();

        for (int i = 0; i < frames.size() && isNotDummyScore(i); i++) {
            int score = frames.get(i).getScore();
            sum += score;
            scores.add(String.valueOf(sum));
        }

        return createFullFrameList(scores);
    }

    private boolean isNotDummyScore(int index) {
        Frame frame = frames.get(index);
        return frame.getScore() != Swing.DUMMY_SCORE;
    }

    private Stream<Frame> frameStream() {
        return frames.stream().limit(FINAL_FRAME_NO);
    }

    private List<String> createFullFrameList(List<String> list) {

        for (int i = list.size(); i < FINAL_FRAME_NO; i++) {
            list.add(BLANK);
        }

        return list;
    }

    public boolean isEnd() {
        return frames.size() > FINAL_FRAME_NO;
    }
}
