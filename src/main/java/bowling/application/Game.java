package bowling.application;

import bowling.domain.state.Pin;
import bowling.ui.BowlingController;
import bowling.ui.Request;
import bowling.ui.Response;

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

    public void bowl(Pin pin) {
        request = request.bowlFallenPins(pin);
        this.response = bowlingController.bowl(request);
    }

    public String getName() {
        return response.getName();
    }

    Response getResponse() {
        return response;
    }

    boolean isEnd() {
        return response.isEnd();
    }

    public int getFrameNumber() {
        return this.response.getFrameNumberLast();
    }

    boolean isSameByFrameNumber(int frameNumber) {
        return response.isSameByFrameNumber(frameNumber);
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
