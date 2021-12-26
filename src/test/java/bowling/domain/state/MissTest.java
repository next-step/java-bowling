package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import bowling.domain.PinsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Pins;

class MissTest {

	@DisplayName("한 프레임 종료 후, 재투구시 예외 확인")
	@Test
	void bowl() {
		// given
		ThrowingState miss = Miss.create(PinsTest.FOUR, PinsTest.FIVE);
		// when & then
		assertThatExceptionOfType(UnsupportedOperationException.class)
			.isThrownBy(() -> miss.bowl(PinsTest.ZERO));
	}

	@DisplayName("한 프레임에서 Miss 후, 종료된 상태 여부 확인")
	@Test
	void isEnd() {
		// given
		ThrowingState miss = Miss.create(PinsTest.FOUR, PinsTest.FIVE);
		// when & then
		assertThat(miss.isEnd()).isTrue();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// given
		ThrowingState miss = Miss.create(PinsTest.FIVE, PinsTest.FOUR);
		ThrowingState missWithGutter = Miss.create(PinsTest.FIVE, PinsTest.ZERO);
		// when & then
		assertAll(
			() -> assertThat(miss.symbol()).isEqualTo(String.format("%s|%s", PinsTest.FIVE.getValue(), PinsTest.FOUR.getValue())),
			() -> assertThat(missWithGutter.symbol()).isEqualTo(String.format("%s|-", PinsTest.FIVE.getValue()))
		);
	}
}
