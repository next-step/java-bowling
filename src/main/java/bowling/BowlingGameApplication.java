package bowling;

public class BowlingGameApplication {
    public static void main(String[] args) {

        Player player = new Player(InputView.inputPlayerName());
        OutputView.printPlayerStatus(player.status());

        int frameOrderToThrow = player.frameOrderToThrow();
        while (frameOrderToThrow != -1) {
            int knockDownCount = InputView.inputKnockDownCount(frameOrderToThrow);
            player.throwBall(knockDownCount);
            frameOrderToThrow = player.frameOrderToThrow();
            OutputView.printPlayerStatus(player.status());
        }
    }
}
