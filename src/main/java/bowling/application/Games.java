package bowling.application;

import bowling.domain.state.Pin;
import bowling.ui.Request;
import bowling.ui.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Games {

    private static final int GAME_END = 1;
    private static final int GAME_FINAL_ROUND = 11;
    private static final int FIRST_REQUEST_INDEX = 0;

    private List<Game> games;
    private List<Response> responses;
    private int currentFrameNumber = 1;

    public Games(List<Request> requests) {
        createGame(requests);
    }

    private void createGame(List<Request> requests) {
        games = new ArrayList<>();
        for (Request request : requests) {
            games.add(new Game(request));
        }
    }

    public void bowl(Pin pin) {
        if (currentFrameNumber <= 9) {
            normalRound(pin);
            return;
        }
        finalRound(pin);
    }

    public String currentBowlPlayerName() {
        for (Game game : games) {
            if (game.isSameByFrameNumber(currentFrameNumber)) {
                return game.getName();
            }
        }
        return games.get(FIRST_REQUEST_INDEX).getName();
    }

    private void normalRound(Pin pin) {
        for (Game game : games) {
            if (game.isSameByFrameNumber(currentFrameNumber)) {
                game.bowl(pin);
                return;
            }
        }
        games.get(FIRST_REQUEST_INDEX).bowl(pin);
        increaseNumber();
    }

    private void finalRound(Pin pin) {
        for (Game game : games) {
            if (!game.isEnd()) {
                game.bowl(pin);
                return;
            }
        }
        end();
    }

    private void end() {
        int size = games.stream()
                .map(Game::isEnd)
                .collect(Collectors.toSet())
                .size();
        if (size == GAME_END) {
            throw new IllegalArgumentException("끝");
        }
    }

    private void increaseNumber() {
        this.currentFrameNumber = currentFrameNumber + 1;
    }

    public boolean isEnd() {
        return currentFrameNumber >= GAME_FINAL_ROUND;
    }

    public List<Response> getResponses() {
        responses = new ArrayList<>();
        for (Game game : games) {
            responses.add(game.getResponse());
        }
        return responses;
    }
}
