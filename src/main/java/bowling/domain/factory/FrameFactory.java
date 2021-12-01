package bowling.domain.factory;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.value.FrameNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameFactory {
    private static final int START_NORMAL_FRAME_NUMBER = 1;
    private static final int END_NORMAL_FRAME_NUMBER = 9;

    public List<Frame> create() {
        List<Frame> frames = new ArrayList<>(createNormalFrame());
        frames.add(createFinalFrame());
        return frames;
    }

    private List<Frame> createNormalFrame() {
        return IntStream.rangeClosed(START_NORMAL_FRAME_NUMBER, END_NORMAL_FRAME_NUMBER)
                .mapToObj(FrameNumber::from)
                .map(NormalFrame::create)
                .collect(Collectors.toList());
    }

    private Frame createFinalFrame() {
        return FinalFrame.create();
    }
}
