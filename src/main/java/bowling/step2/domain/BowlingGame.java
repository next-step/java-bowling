package bowling.step2.domain;


import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;

public class BowlingGame {
    public static final int GAME_START_INDEX = 1;
    public static final int GAME_LAST_INDEX = 10;

    public void startGame(String name) {
        Player player = new Player(name, GAME_START_INDEX, GAME_LAST_INDEX);
        ResultView.printInitScoreBoard(player);
        playGame(player);
    }

    private void playGame(Player player) {
        int frameNum = player.currentFrameNum();
        String score = InputView.inputScore(frameNum);
        player.addScore(score);

        Boolean isEndedOneFrame = player.isEndedOneFrame(frameNum);
        Boolean isFinalFrame = player.isFinalFrame(frameNum);

        if (isEndedOneFrame) {
            ResultView.printGameScoreBoard(player);
        }

        if (!(isEndedOneFrame && isFinalFrame)) {
            playGame(player);
        }
    }
}
