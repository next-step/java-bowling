package bowling.ui;

import bowling.application.BowlingService;
import bowling.domain.frame.Frames;
import bowling.dto.BowlingRequest;
import bowling.dto.BowlingResponse;

public class BowlingController {
    private final BowlingService bowlingService;

    public BowlingController(final BowlingService bowlingService) {
        this.bowlingService = bowlingService;
    }

    public BowlingResponse play(final BowlingRequest request) {
        Frames frames = bowlingService.play(request.toPins());
        return new BowlingResponse(frames, request.toPlayer());
    }
}
