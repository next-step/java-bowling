package bowling.ui;

import bowling.application.BowlingService;
import bowling.application.Response;
import bowling.domain.frame.Frame;

import java.util.List;

public class BowlingController {

    private BowlingService bowlingService;

    public BowlingController(BowlingService bowlingService) {
        this.bowlingService = bowlingService;
    }

    public Response bowl(int pins) {
        List<Frame> frames = bowlingService.bowl(pins);
        return new Response(frames);
    }
}
