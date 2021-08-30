package bowling.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.Bowling;
import bowling.domain.common.Player;

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
	void printFrames(final int pitchCount) {
		// given
		final Player player = new Player("cbh");
		Bowling bowling = Bowling.of();
		for (int i = 0; i < pitchCount; i++) {
			bowling = bowling.pitch(10);
		}

		// when
		OutputView.printFrames(player, bowling);

		// then
	}
}
