package bowling.service.dto;

import bowling.domain.frame.Pin;
import bowling.domain.result.FrameResult;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FrameResultDto {
    private final List<Pin> pins;
    private final int score;

    private FrameResultDto(List<Pin> pins, int score) {
        this.pins = new ArrayList<>(pins);
        this.score = score;
    }

    public static FrameResultDto of(FrameResult result) {
//        String description = result.getStates().stream()
//                .flatMap(states -> states.pins().stream())
//                .map(PinView::valueOf)
//                .map(PinView::getDescription)
//                .collect(Collectors.joining(DELIMITER));
        List<Pin> pins = result.getStates().stream()
                .map(State::pins)
                .flatMap(pins1 -> pins1.stream())
                .collect(Collectors.toList());

        int score = result.getScore().getValue();
        return new FrameResultDto(pins, score);
    }

    public List<Pin> getPins() {
        return Collections.unmodifiableList(pins);
    }

    public int getScore() {
        return score;
    }
}
