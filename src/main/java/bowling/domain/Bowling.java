package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Bowling {
    final List<Frame> frames;

    public Bowling(final List<Frame> frames) {
        this.frames = frames;
    }

    public static Bowling from(final KnockedPinsInput knockedPinsInput, final FramesOutput framesOutput) {
        final List<Frame> frames = new ArrayList<>();

        framesOutput.print(frames);

        Frame frame = initFirst(knockedPinsInput, framesOutput, frames);
        frame = initBody(knockedPinsInput, framesOutput, frames, frame);
        initLast(knockedPinsInput, framesOutput, frames, frame);

        return new Bowling(frames);
    }

    private static Frame initFirst(KnockedPinsInput knockedPinsInput, FramesOutput framesOutput, List<Frame> frames) {
        Frame frame = NormalFrame.init();
        frame = playedFrame(knockedPinsInput, framesOutput, frames, frame, 1);
        frames.add(frame);
        return frame;
    }

    private static Frame initBody(KnockedPinsInput knockedPinsInput, FramesOutput framesOutput,
                                  List<Frame> frames, Frame frame) {
        for (int i = 2; i < 10; i++) {
            frame = frame.next();
            frame = playedFrame(knockedPinsInput, framesOutput, frames, frame, i);
            frames.add(frame);
        }
        return frame;
    }

    private static void initLast(KnockedPinsInput knockedPinsInput, FramesOutput framesOutput,
                                 List<Frame> frames, Frame frame) {
        frame = frame.last();
        frame = playedFrame(knockedPinsInput, framesOutput, frames, frame, 10);
        frames.add(frame);
    }

    private static Frame playedFrame(KnockedPinsInput knockedPinsInput, FramesOutput framesOutput,
                                     List<Frame> frames, Frame frame, int frameIndex) {
        while (frame.playing()) {
            frame = frame.play(KnockedPins.from(knockedPinsInput.count(frameIndex)));

            framesOutput.print(
                    Stream.concat(frames.stream(), Stream.of(frame))
                            .collect(Collectors.toList())
            );
        }

        return frame;
    }
}
