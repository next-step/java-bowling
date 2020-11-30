package bowling.domain;

import bowling.dto.FramesDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class Frames {
    // NOTE: getLast 를 쓰기 위해 LinkedList 로 선언
    private final LinkedList<Frame> frames = new LinkedList<>();

    int size() {
        return frames.size();
    }

    List<Frame> subList(int from, int to) {
        return frames.subList(from, to);
    }

    void update(Rolls rolls) {
        if (isLastFinished()) {
            frames.add(Frame.of(rolls));
            return;
        }
        last().update(rolls);
    }

    boolean isBonus() {
        return !frames.isEmpty()
                && last().isBonus();
    }

    boolean isLastFinished() {
        return frames.isEmpty()
                || last().isFinished();
    }

    private Frame last() {
        return frames.getLast();
    }

    FramesDto exportFramesDto() {
        return frames.stream()
                .map(Frame::exportFrameDto)
                .collect(collectingAndThen(toList(), FramesDto::new));
    }
}
