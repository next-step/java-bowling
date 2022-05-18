package bowling.view.dto;

import bowling.model.Bowlings;

import java.util.List;
import java.util.stream.Collectors;

public final class BowlingResponse {

    private final String name;
    private final FramesResponse frames;

    private BowlingResponse(String name, FramesResponse frames) {
        this.name = name;
        this.frames = frames;
    }

    private static BowlingResponse from(String name, FramesResponse frames) {
        return new BowlingResponse(name, frames);
    }

    public static List<BowlingResponse> listFrom(Bowlings bowlings) {
        return bowlings.list()
                .stream()
                .map(bowling -> from(bowling.participant().name(), FramesResponse.from(bowling.frames())))
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public FramesResponse getFrames() {
        return frames;
    }
}
