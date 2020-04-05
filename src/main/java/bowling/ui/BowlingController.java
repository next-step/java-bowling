package bowling.ui;

import bowling.application.BowlingService;
import bowling.application.Request;
import bowling.application.Response;
import bowling.domain.frame.Bowling;

public class BowlingController {

    private BowlingService bowlingService;

    public BowlingController(BowlingService bowlingService) {
        this.bowlingService = bowlingService;
    }

    public Response bowl(Request request) {
        Bowling bowling = bowlingService.bowl(request);
        return new Response(bowling);
    }
}
