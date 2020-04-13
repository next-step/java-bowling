package bowling.application;

import bowling.domain.state.Pin;
import bowling.ui.BowlingController;

public class Game {

    private Request request;
    private BowlingController bowlingController;
    private Response response;

    public Game(Request request) {
        this.request = request;
        this.bowlingController = new BowlingController(new BowlingService());
        this.response = bowlingController.bowl(request);
    }

    public void bowl(Pin pin) {
        request = request.bowlFallenPins(pin);
        this.response = bowlingController.bowl(request);
    }

    public Response getResponse() {
        return response;
    }

    public boolean isFinish() {
        return this.response.isFinish();
    }
}
