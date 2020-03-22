package bowling.ui;

import bowling.application.BowlingService;
import bowling.application.Response;
import bowling.domain.Bowling;


public class BowlingController {

    private BowlingService bowlingService;

    public BowlingController() {
        this.bowlingService = new BowlingService();
    }

    public Response bowl(Bowling bowlingRequest, int hit) {
        Bowling bowling = bowlingService.bowl(bowlingRequest, hit);
        return new Response(bowling);
    }
}
