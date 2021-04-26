package bowling.dto;

import bowling.domain.engine.frame.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class FinalFrameExporter {

    private FinalFrameExporter() {
    }

    public static String export(List<State> states) {
        return states.stream()
                     .map(State::export)
                     .collect(Collectors.joining("|"));
    }
}
