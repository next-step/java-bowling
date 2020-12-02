package bowling.domain;

import bowling.dto.FramesDto;

import java.util.LinkedList;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class Frames {
    // NOTE: getLast 를 쓰기 위해 LinkedList 로 선언
    private final LinkedList<Frame> frames = new LinkedList<>();

    List<Frame> subList(int from, int to) {
        return frames.subList(from, to);
    }

    int size() {
        return frames.size();
    }

    void update(Rolls rolls) {
        if (size() > MAX_FRAME_NO
                || size() == MAX_FRAME_NO && isLastFinished()) {
            return;
        }
        if (isLastFinished()) {
            frames.add(Frame.of(rolls));
            return;
        }
        last().update(rolls);
    }

    private boolean isLastFinished() {
        return frames.isEmpty()
                || last().getFrameEnum() != FrameEnum.UNFINISHED;
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
