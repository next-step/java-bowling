package bowling.application;

import bowling.domain.state.Pin;

import java.util.ArrayList;
import java.util.List;

public class Games {

    private List<Game> games;
    private List<Response> responses;
    private int currentFrameNumber = 1;

    public Games(List<Game> games) {
        this.games = games;
    }

    public void bowl(Pin pin) {
        for (Game game : games) {
            if (isSameByFrameNumber(game.getFrameNumber())) {
                game.bowl(pin);
                return;
            }
        }
        games.get(0).bowl(pin);
        increaseNumber();
    }

    private boolean isSameByFrameNumber(int frameNumber) {
        return currentFrameNumber == frameNumber;
    }

    private void increaseNumber() {
        this.currentFrameNumber = currentFrameNumber + 1;
    }

    public List<Response> getResponses() {
        responses = new ArrayList<>();
        for (Game game : games) {
            responses.add(game.getResponse());
        }
        return responses;
    }
}
