package bowling;

import bowling.frame.Frame;
import bowling.frame.FrameScoreType;
import bowling.frame.FrameType;

import java.util.LinkedList;
import java.util.List;

public class Frames {
    public static final int FRAME_COUNT = 10;
    private static final String FRAME_COUNT_EXCEPTION = "프레임은 10번을 넘을 수 없습니다";
    private List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
    }

    public Frame roll(int pins) {
        if (frames.size() == 0) {
            Frame frame = Frame.initialOf(pins);
            frames.add(frame);
            return frame;
        }

        int index = lastOfFrames();

        if (index < FRAME_COUNT) {
            if (index == frames.size()) {
                Frame frame = frames.get(index - 1);
                frame.addScore(pins);
                return frame;
            }

            Frame frame = Frame.of(pins, frames.get(index - 2));
            frames.add(frame);
            return frame;
        }

        if (index == frames.size()) {
            Frame frame = frames.get(index - 1);
            if (frame.getFrameScoreType() == FrameScoreType.PENDING) {
                frame.addScore(pins);
            } else {
                frame.addBonus(pins);
            }
            return frame;
        }

        if (index > FRAME_COUNT) {
            throw new IllegalArgumentException(FRAME_COUNT_EXCEPTION);
        }

        if (this.frames.stream().anyMatch(frame -> frame.getFrameType() == FrameType.FINAL)) {
            throw new IllegalArgumentException(FRAME_COUNT_EXCEPTION);
        }

        Frame frame = Frame.finalOf(pins, frames.get(index - 2));
        frames.add(frame);
        return frame;
    }

    public int lastOfFrames() {
        Frame frame = frames.get(frames.size() - 1);
        FrameScoreType frameScoreType = frame.getFrameScoreType();
        if (frameScoreType == FrameScoreType.PENDING) {
            return frames.size();
        }
        FrameType frameType = frame.getFrameType();
        if (frameType != FrameType.FINAL || frameScoreType == FrameScoreType.MISS) {
            return frames.size() + 1;
        }
        if (frame.getBonus() == null || frame.getBonus().getNeedScoreCount() > 0) {
            return frames.size();
        }
        return frames.size() + 1;
    }

    public List<Frame> getFrames() {
        return this.frames;
    }
}