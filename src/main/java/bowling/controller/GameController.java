package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Shot;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameController {
    private static final int START_FRAME = 1;

    public void run() {
        int playerCount = InputView.extractPlayerCount();

        List<Game> games = makeGames(playerCount);
        int frameIndex = START_FRAME;

        showCurrentGameFrame(games);
        while (isNotFinished(games)) {
            frameIndex = getCurrentGameFrame(games, frameIndex);
            Game current = getCurrentUser(games, frameIndex);
            play(current);
            showCurrentGameFrame(games);
        }
    }

    private int getCurrentGameFrame(List<Game> games, int index) {
        boolean isNotChangedFrame = games
                .stream()
                .anyMatch((game -> game.getCurrentFrameIndex() == index));

        if (isNotChangedFrame) {
            return index;
        }

        return index + 1;
    }

    private Game getCurrentUser(List<Game> games, int index) {
        return games.stream()
                .filter((game -> game.isNotFinished()))
                .filter((game -> game.getCurrentFrameIndex() == index))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Game's frame is not invalid"));
    }

    private void play(Game game) {
        int downCount = InputView.extractFrameResult(game.getUser());

        Shot shot = new Shot(downCount);
        game.play(shot);
    }

    private boolean isFinished(List<Game> games) {
        return games.stream().allMatch((game -> game.isFinished()));
    }

    private boolean isNotFinished(List<Game> games) {
        return !isFinished(games);
    }

    private List<Game> makeGames(int playerCount) {
        return IntStream.range(0, playerCount)
                .mapToObj((index) -> makeGame(index))
                .collect(Collectors.toList());
    }

    private Game makeGame(int index) {
        String username = InputView.extractUsername(index + 1);
        return Game.of(new User(username));
    }

    public void showCurrentGameFrame(List<Game> games) {
        OutputView.showFrames();

        games.stream().forEach((game) -> {
            OutputView.showGameFrames(game);
            OutputView.showScoreFrames(game);
        });
    }
}
