package bowling.domain.common;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.domain.common.exception.InvalidPinCountException;

@DisplayName("핀")
class PinsTest {

	@DisplayName("생성 - 유효하지 않은 핀 개수")
	@ParameterizedTest
	@ValueSource(ints = {
		-1, 11
	})
	void create_invalidPinCount(final int count) {
		// given

		// when
		assertThrows(InvalidPinCountException.class, () -> Pins.of(count));

		// then
	}

	@DisplayName("쓰러진 핀 개수")
	@ParameterizedTest
	@ValueSource(ints = {
		1, 10
	})
	void getHitCount(final int count) {
		// given
		final Pins pins = Pins.of(count);

		// when
		final int hitCount = pins.getHitCount();

		// then
		assertThat(hitCount).isEqualTo(count);
	}

	@DisplayName("모든 핀을 쓰러뜨렸는지 확인")
	@ParameterizedTest
	@CsvSource(value = {
		"1,false",
		"10,true"
	})
	void isAllHit(final int count, final boolean expected) {
		// given
		final Pins pins = Pins.of(count);

		// when
		final boolean result = pins.isAllHit();

		// then
		assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> add() {
		return Stream.of(
			Arguments.of(Pins.of(0), Pins.of(10), 10),
			Arguments.of(Pins.of(10), Pins.of(0), 10),
			Arguments.of(Pins.of(2), Pins.of(4), 6),
			Arguments.of(Pins.of(5), Pins.of(2), 7)
		);
	}

	@DisplayName("핀끼리 더하기")
	@ParameterizedTest
	@MethodSource
	void add(final Pins pins1, final Pins pins2, final int expected) {
		// given

		// when
		final Pins result = pins1.add(pins2);

		// then
		assertThat(result.getHitCount()).isEqualTo(expected);
	}
}
