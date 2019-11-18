package domain.bowlling;

/**
 * 한 사람에게 할당되는 볼링 점수판 한 줄
 */
public class BowlingOnBoard {

	private final BowlingBoard bowlingBoard = new BowlingBoard();
	private final String userName;

	public BowlingOnBoard(String userName) {
		this.userName = userName;
	}

}
