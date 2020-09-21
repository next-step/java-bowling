package bowling.controller;

import bowling.model.scoreboard.ScoreBoard;
import bowling.model.frame.Frame;
import bowling.model.frame.Frames;
import bowling.model.Player;
import bowling.util.ValidationUtils;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class BowlingGame {
    InputView inputView;
    ResultView resultView;

    public BowlingGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void start() {
        int playerCount = tryUntilSuccess(this::getPlayerCount);
        ScoreBoard scoreBoard = new ScoreBoard();
        IntStream.range(0, playerCount)
                .forEach(i -> tryUntilSuccess(() -> addPlayer(scoreBoard, i + 1)));

        Collection<Player> players = scoreBoard.getPlayers();
        while(!scoreBoard.isEnded()) {
            players.forEach(player -> playTurn(player, scoreBoard));
        }
    }

    private void playTurn(Player player, ScoreBoard scoreBoard) {
        Frames frames = scoreBoard.get(player);
        Frame currentFrame = frames.getCurrentFrame();
        while (!currentFrame.isEnded()) {
            tryUntilSuccess(() -> addResult(player, currentFrame));
            resultView.printHeader();
            resultView.printScoreBoard(scoreBoard.toDto());
        }
    }

    private Frame addResult(Player player, Frame frame) {
        int count = inputView.inputFramePinCount(player);
        frame.addResult(count);
        return frame;
    }

    private ScoreBoard addPlayer(ScoreBoard scoreBoard, int index) {
        Player player = new Player(inputView.inputPlayerName(index));
        scoreBoard.add(player);
        return scoreBoard;
    }

    private int getPlayerCount() {
        int playerCount = inputView.inputPlayerCount();
        if (playerCount <= 0) {
            throw new IllegalArgumentException("0보다 큰 수를 입력해주세요.");
        }
        return playerCount;
    }

    private <T> T tryUntilSuccess(Supplier<T> supplier) {
        return ValidationUtils.tryUntilSuccess(supplier, resultView::printError);
    }
}
