package bowling.view;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.BowlingGame;
import bowling.view.dto.PrintBowlerDto;

@DisplayName("출력")
class OutputViewTest {

	@ParameterizedTest
	@CsvSource({
		"12",
		"11",
		"10",
		"9",
		"7",
		"5",
		"3",
		"1",
		"0",
	})
	void resultBoard(final int pitchCount) {
		// given
		final BowlingGame bowlingGame = BowlingGame.start(Collections.singletonList("cbh"));
		for (int i = 0; i < pitchCount; i++) {
			bowlingGame.play(10);
		}

		// when
		OutputView.printResultBoard(PrintBowlerDto.of(bowlingGame.getBowlers()));

		// then

	}
}
