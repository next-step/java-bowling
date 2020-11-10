package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PinTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void 핀을_가져온다(int input) {
		Pin pin = new Pin(input);
		assertThat(pin).isEqualTo(new Pin(input));
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 11})
	void 잘못된_숫자의_핀을_가져오면_오류를_반환한다(int input) {
		assertThatThrownBy(() -> new Pin(input))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 다음_핀을_가져온다() {
		Pin pin = new Pin(9);
		Pin nextPin = pin.next(1);
		assertThat(nextPin).isEqualTo(new Pin(1));
	}

	@Test
	void 잘못된_숫자의_다음_핀을_가져오면_오류를_반환한다() {
		Pin pin = new Pin(9);
		assertThatThrownBy(() -> pin.next(2))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 핀이_10개가_되면_게임을_끝낸다(){
		Pin pin = new Pin(10);
		assertThat(pin.isEnd()).isTrue();
	}

	@Test
	void 스트라이크_심볼을_알려준다() {
		Pin pin = new Pin(10);
		assertThat(pin.getSymbol()).isEqualTo(ScoreSymbol.STRIKE);
	}

	@Test
	void 미스_심볼을_알려준다() {
		Pin pin = new Pin(0);
		assertThat(pin.getSymbol()).isEqualTo(ScoreSymbol.MISS);
	}

	@ParameterizedTest
	@MethodSource("parameters")
	void 점수에_따라_심볼을_알려준다(int first, int second, ScoreSymbol expect) {
		Pin pin = new Pin(first);
		Pin nextPin = pin.next(second);
		assertThat(nextPin.getSymbol()).isEqualTo(expect);
	}

	private static Stream<Arguments> parameters() {
		return Stream.of(
			Arguments.of(1, 9, ScoreSymbol.SPARE),
			Arguments.of(5, 5, ScoreSymbol.SPARE),
			Arguments.of(0, 0, ScoreSymbol.GUTTER),
			Arguments.of(1, 2, ScoreSymbol.MISS)
		);
	}
}
