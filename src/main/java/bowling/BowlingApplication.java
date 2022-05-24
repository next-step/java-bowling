package bowling;

public class BowlingApplication {

	private static final InputView INPUT_VIEW = new InputView();
	private static final OutputView OUTPUT_VIEW = new OutputView();

	public static void main(String[] args) {
		Player player = new Player(INPUT_VIEW.askPlayerName());

		OUTPUT_VIEW.showInitialized();

	}
}
