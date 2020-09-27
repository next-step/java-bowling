package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int LAST_INDEX_GAP = 1;
    private static final int FINAL_FRAME_NO = 10;
    private static final String BLANK = "";

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

        if (frame.isEndedFrame() && frames.size() <= 10) {
            frames.add(FrameFactory.next(frame, frames.size()));
        }
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - LAST_INDEX_GAP);
    }

    public List<String> getSwingHistories() {

        List<String> swingHistories = frames.stream()
                                            .limit(FINAL_FRAME_NO)
                                            .map(Frame::swingHistoryToString)
                                            .collect(toList());

        for (int i = frames.size(); i < FINAL_FRAME_NO; i++) {
            swingHistories.add(BLANK);
        }

        return swingHistories;
    }

    public List<String> getScores() {

        List<String> scores = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (i >= frames.size()) {
                break;
            }

            Frame frame = frames.get(i);

            if (frame == null || frame.isNotSwing()) {
                scores.add(BLANK);
                continue;
            }

            int score = frame.getScore();

            if (!frame.isSpare() && !frame.isStrike()) {
                scores.add(String.valueOf(score));
                continue;
            }

            Frame next = frames.get(i + 1);

            if (next == null || next.isNotSwing()) {
                scores.add(BLANK);
                continue;
            }

            if (frame.isSpare()) {
                scores.add(String.valueOf(score + next.firstSwing()));
                continue;
            }

            if (frame.isStrike()) {
                scores.add(String.valueOf(score + next.getScore()));
            }
        }

        if (frames.size() == 10) {
            scores.add(BLANK);
        }

        for (int i = frames.size(); i < FINAL_FRAME_NO; i++) {
            scores.add(BLANK);
        }

        return scores;
    }

    public boolean isEnd() {
        return frames.size() > FINAL_FRAME_NO;
    }
}
