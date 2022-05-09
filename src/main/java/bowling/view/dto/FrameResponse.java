package bowling.view.dto;

import bowling.model.frame.Frame;
import bowling.model.frame.Frames;
import bowling.model.frame.NormalFrame;
import bowling.model.frame.finalization.FinalFrame;

import java.util.List;
import java.util.stream.Collectors;

public final class FrameResponse {

    private final StateResponse state;
    private final boolean isFinal;

    private FrameResponse(StateResponse state, boolean isFinal) {
        this.state = state;
        this.isFinal = isFinal;
    }

    public static List<FrameResponse> listFrom(Frames frames) {
        return frames.list()
                .stream()
                .map(FrameResponse::from)
                .collect(Collectors.toList());
    }

    private static FrameResponse from(Frame frame) {
        if (frame instanceof NormalFrame) {
            return new FrameResponse(StateResponse.from(((NormalFrame) frame).state()), false);
        }
        if (frame instanceof FinalFrame) {
            return new FrameResponse(StateResponse.from(((FinalFrame) frame).state()), false);
        }
        throw new IllegalArgumentException(String.format("frame type(%s) is not supported", frame.getClass()));
    }

    public StateResponse getState() {
        return state;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
