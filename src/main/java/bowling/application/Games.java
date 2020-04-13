package bowling.application;

import bowling.domain.state.Pin;

import java.util.ArrayList;
import java.util.List;

public class Games {

    private List<Game> games;
    private List<Response> responses;

    public Games(List<Game> games) {
        this.games = games;
    }

    public void bowl(Pin pin) {
        for (Game game : games) {
            if (isFinish()) {
                continue;
            }
            game.bowl(pin);
            return;
        }
    }

    public List<Response> getResponses() {
        responses = new ArrayList<>();
        for (Game game : games) {
            responses.add(game.getResponse());
        }
        return responses;
    }

    public boolean isFinish() {
        boolean finish = true;
        for (Game game : games) {
            if (!game.isFinish()) {
                finish = false;
            }
        }
        return finish;
    }
}
