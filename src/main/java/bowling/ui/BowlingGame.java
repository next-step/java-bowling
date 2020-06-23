package bowling.ui;

import bowling.domain.Player;

public class BowlingGame {
    public static void main(String[] args) {
        Player initPlayer = initGame();

        // 1 프레임 처리
        FirstFrameController firstFrameController = new FirstFrameController(initPlayer);
        Player player = firstFrameController.doFirstFrame();

        // 2 ~ 9 프레임 처리
        player = doMiddleFrame(player);

        // 10 프레임 처리
        doFinalFrame(player);
    }

    private static Player initGame() {
        String userName = InputView.getPlayerName();

        OutputView.printEmptyResult(userName);

        return Player.createByName(userName);
    }

    private static Player doMiddleFrame(Player player) {
        while(true) {
            player = doInCurrentFrame(player);
            if (isBeforeFinal(player)) {
                break;
            }
            player = doNextFrame(player);
        }
        return player;
    }

    private static Player doNextFrame(Player player) {
        player = player.toNextFrame(
                InputView.getNumberOfHitPin(player.getCurrentFrameIndex() + 1));

        OutputView.printPlayerResult(player);
        return player;
    }

    private static Player doInCurrentFrame(Player player) {
        while (!player.isCurrentFrameCompleted()) {
            player = player.bowlCurrentFrame(
                    InputView.getNumberOfHitPin(player.getCurrentFrameIndex()));

            OutputView.printPlayerResult(player);
        }
        return player;
    }

    private static void doFinalFrame(Player player) {
        player = player.toFinalFrame(InputView.getNumberOfHitPin(10));

        OutputView.printPlayerResult(player);

        doFinalFrameInProgress(player);
    }

    private static void doFinalFrameInProgress(Player player) {
        while(!player.isCurrentFrameCompleted()) {
            player = player.bowlCurrentFrame(InputView.getNumberOfHitPin(10));

            OutputView.printPlayerResult(player);
        }
    }

    private static boolean isBeforeFinal(Player player) {
        return (player.getCurrentFrameIndex() == 9) && (player.isCurrentFrameCompleted());
    }
}
