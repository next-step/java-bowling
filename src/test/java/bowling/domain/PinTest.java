package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
}
