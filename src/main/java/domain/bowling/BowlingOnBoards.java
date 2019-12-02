package domain.bowling;

import domain.BowlingPins;
import domain.UserName;
import domain.UserNumber;

import java.util.ArrayList;
import java.util.List;

class BowlingOnBoards {

	private final List<BowlingOnBoard> bowlingOnBoards;
	private final UserNumber userNumber;

	BowlingOnBoards(UserNumber userNumber) {
		this.bowlingOnBoards = new ArrayList<>();
		this.userNumber = userNumber;
	}

	void enroll(UserName userName) {
		validateBowlingOnBoards();
		bowlingOnBoards.add(new BowlingOnBoard(userName));
	}

	private void validateBowlingOnBoards() {
		if (userNumber.isExceedUserNumber(bowlingOnBoards.size())) {
			throw new IllegalStateException(String.format("정해진 유저 숫자(%s)를 초과했습니다", userNumber.getUserNumber()));
		}
	}

	int roll(BowlingPins pins, int gameTurnIndex) {
		if (bowlingOnBoards.get(gameTurnIndex).roll(pins)) {
			return (gameTurnIndex + 1) % userNumber.getUserNumber();
		}
		return gameTurnIndex;
	}

	boolean isNotEnd() {
		return bowlingOnBoards.stream()
				.anyMatch(BowlingOnBoard::isNotEnd);
	}

	UserName getUserName(int gameTurnIndex) {
		return bowlingOnBoards.get(gameTurnIndex).getUserName();
	}

	List<BowlingOnBoard> getBowlingOnBoard() {
		return bowlingOnBoards;
	}

}
