package bowling.application;

import bowling.domain.state.Pin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Games {

    private static final int GAME_END = 1;

    private List<Game> games;
    private List<Response> responses;
    private int currentFrameNumber = 1;

    public Games(List<Game> games) {
        this.games = games;
    }

    public void bowl() {
        if (currentFrameNumber <= 9) {
            for (Game game : games) {
                if (isSameByFrameNumber(game.getFrameNumber())) {
                    game.bowl();
                    return;
                }
            }
            games.get(0).bowl();
            increaseNumber();
            return;
        } else {
            for (Game game : games) {
                if (!game.getResponse().isEnd()) {
                    game.bowl();
                    return;
                }
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

    public List<Response> getResponses() {
        responses = new ArrayList<>();
        for (Game game : games) {
            responses.add(game.getResponse());
        }
        return responses;
    }
}
