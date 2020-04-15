package bowling.domain;

import bowling.dto.FramesDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Frames {
    private static final int MAX_FRAMES = 10;

    private final List<Frame> frames;

    Frames() {
        this.frames = new ArrayList<>();
        frames.add(Frame.init());
    }

    int getCurrentFrameNumber() {
        if (isCurrentFrameDone()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    private boolean isCurrentFrameDone() {
        return getLast().isFrameClosed();
    }

    private Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    FramesDto getDto() {
        return new FramesDto(frames.stream()
                .map(Frame::getDto)
                .collect(Collectors.toList()));
    }

    void shot(int shot) {
        if (isCurrentFrameDone()) {
            frames.add(getNextFrame(shot));
            return;
        }

        getLast().shot(shot);
    }

    private Frame getNextFrame(int shot) {
        if (frames.size() < MAX_FRAMES - 1) {
            return getLast().next(shot);
        }
        return getLast().last(shot);
    }

    boolean isGameSet() {
        return frames.stream()
                .filter(Frame::isFrameClosed)
                .count() == MAX_FRAMES;
    }
}
