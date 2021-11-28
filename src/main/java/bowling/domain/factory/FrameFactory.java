package bowling.domain.factory;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.value.FrameNumber;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class FrameFactory {
    private static final int START_NORMAL_FRAME_NUMBER = 1;
    private static final int END_NORMAL_FRAME_NUMBER = 9;

    public Frame create() {
        AtomicReference<Frame> frame = new AtomicReference<>(FinalFrame.create());

        IntStream.rangeClosed(START_NORMAL_FRAME_NUMBER, END_NORMAL_FRAME_NUMBER)
                .boxed()
                .sorted(Collections.reverseOrder())
                .forEach(v -> {
                    Frame prevFrame = NormalFrame.create(FrameNumber.from(v));
                    prevFrame.setNext(frame.get());
                    frame.set(prevFrame);
                });

        return frame.get();
    }
}
