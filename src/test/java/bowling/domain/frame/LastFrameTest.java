package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Index;
import bowling.domain.Pins;

class LastFrameTest {
	@DisplayName("3strike")
	@Test
	void threeStrike() {
		verify(Arrays.asList(10, 10, 10), true, 3, "X|X|X");
	}

	@DisplayName("2strike 5pins")
	@Test
	void twoStrikeFivePins() {
		verify(Arrays.asList(10, 10, 5), true, 3, "X|X|5");
	}

	@DisplayName("1strike spare")
	@Test
	void oneStrikeSpare() {
		verify(Arrays.asList(10, 5, 5), true, 2, "X|5|/");
	}

	@DisplayName("1strike miss")
	@Test
	void oneStrikeMiss() {
		verify(Arrays.asList(10, 5, 3), true, 2, "X|5|3");
	}

	@DisplayName("1spare 5pins")
	@Test
	void oneSpareFivePins() {
		verify(Arrays.asList(5, 5, 5), true, 2, "5|/|5");
	}

	@DisplayName("miss")
	@Test
	void miss() {
		verify(Arrays.asList(5, 4), true, 1, "5|4");
	}

	@DisplayName("miss gutter")
	@Test
	void missWithGutter() {
		verify(Arrays.asList(5, 0), true, 1, "5|-");
	}

	@DisplayName("2strike")
	@Test
	void twoStrike() {
		verify(Arrays.asList(10, 10), false, 2, "X|X");
	}

	@DisplayName("5pins")
	@Test
	void fivePins() {
		verify(Collections.singletonList(5), false, 1, "5");
	}

	@DisplayName("nothing")
	@Test
	void nothing() {
		verify(Collections.emptyList(), false, 1, "");
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

	private void verify(List<Integer> pinNumbers, boolean end, int size, String symbol) {
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
			() -> assertThat(lastFrame.symbol()).isEqualTo(symbol)
		);
	}
}
