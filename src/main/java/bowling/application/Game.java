package bowling.application;

import bowling.domain.state.Pin;
import bowling.ui.BowlingController;
import bowling.view.InputView;

import java.util.Objects;

public class Game {

    private Request request;
    private BowlingController bowlingController;
    private Response response;

    public Game(Request request) {
        this.request = request;
        this.bowlingController = new BowlingController(new BowlingService());
        this.response = bowlingController.bowl(request);
    }

    public void bowl() {
        Pin pin = new Pin(InputView.inputBowl(request.getName()));
        request = request.bowlFallenPins(pin);
        this.response = bowlingController.bowl(request);
    }

    public Response getResponse() {
        return response;
    }

    public int getFrameNumber() {
        return this.response.getFrameNumberLast();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(request, game.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request);
    }
}
