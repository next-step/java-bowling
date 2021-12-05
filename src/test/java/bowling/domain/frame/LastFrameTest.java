package bowling.domain.frame;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Index;
import bowling.domain.Pins;
import bowling.domain.Score;

class LastFrameTest {
	@DisplayName("3strike")
	@Test
	void threeStrike() {
		verify(asList(10, 10, 10), true, 3, "X|X|X", 30);
	}

	@DisplayName("2strike 5pins")
	@Test
	void twoStrikeFivePins() {
		verify(asList(10, 10, 5), true, 3, "X|X|5", 25);
	}

	@DisplayName("1strike spare")
	@Test
	void oneStrikeSpare() {
		verify(asList(10, 5, 5), true, 2, "X|5|/", 20);
	}

	@DisplayName("1strike miss")
	@Test
	void oneStrikeMiss() {
		verify(asList(10, 5, 3), true, 2, "X|5|3", 18);
	}

	@DisplayName("1spare 1strike")
	@Test
	void oneSpareOneStrike() {
		verify(asList(5, 5, 10), true, 2, "5|/|X", 20);
	}

	@DisplayName("1spare 5pins")
	@Test
	void oneSpareFivePins() {
		verify(asList(5, 5, 5), true, 2, "5|/|5", 15);
	}

	@DisplayName("miss")
	@Test
	void miss() {
		verify(asList(5, 4), true, 1, "5|4", 9);
	}

	@DisplayName("miss gutter")
	@Test
	void missWithGutter() {
		verify(asList(5, 0), true, 1, "5|-", 5);
	}

	@DisplayName("2strike")
	@Test
	void twoStrike() {
		verify(asList(10, 10), false, 2, "X|X", Score.INCALCULABLE_SCORE);
	}

	@DisplayName("5pins")
	@Test
	void fivePins() {
		verify(singletonList(5), false, 1, "5", Score.INCALCULABLE_SCORE);
	}

	@DisplayName("ready")
	@Test
	void ready() {
		verify(emptyList(), false, 1, "", Score.INCALCULABLE_SCORE);
	}

	@DisplayName("LastFrame 의 index 검증")
	@Test
	void getFrameIndex() {
		// given
		LastFrame lastFrame = LastFrame.initialize();

		// when
		int frameIndex = lastFrame.getFrameIndex();

		// then
		assertThat(frameIndex).isEqualTo(Index.MAX_OF_INDEX);
	}

	private void verify(List<Integer> pinNumbers, boolean end, int size, String symbol, int score) {
		// given
		LastFrame lastFrame = LastFrame.initialize();

		// when
		for (int pinNumber : pinNumbers) {
			lastFrame.bowl(Pins.create(pinNumber));
		}

		// then
		assertAll(
			() -> assertThat(lastFrame.isEnd()).isEqualTo(end),
			() -> assertThat(lastFrame.size()).isEqualTo(size),
			() -> assertThat(lastFrame.symbol()).isEqualTo(symbol),
			() -> assertThat(lastFrame.score()).isEqualTo(score)
		);
	}
}
