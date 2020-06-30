package camp.nextstep.edu.nextstep8.bowling;

public class BowlingGameApplication {
    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();
        String player = BowlingGameInput.getPlayer();

        while (bowlingGame.hasNextFrame() || bowlingGame.hasLastChance()) {
            BowlingGameView.showDashboard(player, bowlingGame.getScoreBoard());
            bowlingGame.roll();
        }

        BowlingGameView.showDashboard(player, bowlingGame.getScoreBoard());
    }
}
