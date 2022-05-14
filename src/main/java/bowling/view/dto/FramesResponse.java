package bowling.view.dto;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.model.frame.FinalFrame;
import bowling.model.frame.Frame;
import bowling.model.frame.FrameState;
import bowling.model.frame.Frames;
import bowling.model.frame.NormalFrame;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.NotThrown;
import bowling.model.frame.state.SecondThrown;
import bowling.model.frame.state.Spare;
import bowling.model.frame.state.Strike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class FramesResponse {

    private final List<String> states;
    private final List<Integer> scores;

    private FramesResponse(List<String> states, List<Integer> scores) {
        this.states = states;
        this.scores = scores;
    }

    public static FramesResponse from(Frames frames) {
        return new FramesResponse(frames.list()
                .stream()
                .map(FramesResponse::stateMarks)
                .collect(Collectors.toList()),
                frames.accumulatedScores()
        );
    }

    static String stateMarks(Frame frame) {
        if (frame instanceof NormalFrame) {
            return normalFrameResponse((NormalFrame) frame);
        }
        if (frame instanceof FinalFrame) {
            return finalFrameResponse((FinalFrame) frame);
        }
        throw new IllegalArgumentException(String.format("frame type(%s) is not supported", frame.getClass()));
    }

    private static String normalFrameResponse(NormalFrame frame) {
        FrameState state = frame.state();
        return BallStateMarkConverter.toString(state.state(), mapCount(extractPins(state.state())));
    }

    private static String finalFrameResponse(FinalFrame frame) {
        FrameState state = frame.state();
        List<Pins> pins = new ArrayList<>(extractPins(state.state()));
        pins.addAll(frame.additionHitPinsGroup());
        return BallStateMarkConverter.toString(state.state(), mapCount(pins), mapCount(frame.additionHitPinsGroup()));
    }

    private static List<Pins> extractPins(BallState state) {
        if (state instanceof Strike) {
            return Collections.singletonList(Pins.MAX);
        }
        if (state instanceof Spare) {
            return Collections.singletonList(((Spare) state).firstHit());
        }
        if (state instanceof FirstThrown) {
            return Collections.singletonList(((FirstThrown) state).countOfHit());
        }
        if (state instanceof SecondThrown) {
            SecondThrown secondThrown = (SecondThrown) state;
            return Arrays.asList(secondThrown.firstHit(), secondThrown.secondHit());
        }
        if (state instanceof NotThrown) {
            return Collections.emptyList();
        }
        throw new IllegalArgumentException(String.format("state type(%s) is not supported", state.getClass()));
    }

    private static List<Integer> mapCount(List<Pins> pinsGroup) {
        return pinsGroup.stream()
                .map(Pins::count)
                .collect(Collectors.toList());
    }

    public List<String> getStates() {
        return states;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
