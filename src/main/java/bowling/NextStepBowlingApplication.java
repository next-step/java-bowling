package bowling;

public class NextStepBowlingApplication {
	public static void main(String[] args) {
		BowlingGame bowlingGame = new BowlingGame();
		BowlingGameController gameController = new BowlingGameController(bowlingGame);
		gameController.playGame();
	}
}
