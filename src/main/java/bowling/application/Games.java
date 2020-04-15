package bowling.application;

import bowling.view.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Games {

    private static final int GAME_END = 1;
    private static final int GAME_FINAL_ROUND = 11;

    private List<Game> games;
    private List<Response> responses;
    private int currentFrameNumber = 1;

    public Games(List<Game> games) {
        this.games = games;
    }

    public Games(int peopleCount) {
        this.games = new ArrayList<>();
        createGame(peopleCount);
    }

    private void createGame(int peopleCount) {
        for (int i = 0; i < peopleCount; i++) {
            Request request = new Request(InputView.inputName(i + 1));
            Game game = new Game(request);
            games.add(game);
        }
    }

    public void bowl() {
        if (currentFrameNumber <= 9) {
            normalRound();
            return;
        }
        finalRound();
    }

    private void normalRound() {
        for (Game game : games) {
            if (isSameByFrameNumber(game.getFrameNumber())) {
                game.bowl();
                return;
            }
        }
        games.get(0).bowl();
        increaseNumber();
    }

    private void finalRound() {
        for (Game game : games) {
            if (!game.getResponse().isEnd()) {
                game.bowl();
                return;
            }
        }
        end();
    }

    private void end() {
        int size = games.stream()
                .map(game -> game.getResponse().isEnd())
                .collect(Collectors.toSet())
                .size();
        if (size == GAME_END) {
            throw new IllegalArgumentException("끝");
        }
    }

    private boolean isSameByFrameNumber(int frameNumber) {
        return currentFrameNumber == frameNumber;
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
