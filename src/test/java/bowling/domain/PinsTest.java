package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinsTest {

	private Pins pins;

	@BeforeEach
	void setUp() {
		pins = new Pins(1);
	}

	@DisplayName("투구 테스트")
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void playTest(int playSize) {
		pins.play(playSize);
		assertThat(pins.pinStatus().totalPin()).isEqualTo(playSize);
	}

	@Test
	@DisplayName("비정상 투구 테스트 : 10개 초과")
	void invalidPlayTest() {
		assertThatThrownBy(() -> {
			pins.play(5);
			pins.play(6);
		}).isInstanceOf(IllegalArgumentException.class).hasMessage("쓰러트릴 핀 갯수는 총 10개를 넘을 수 없습니다.");
	}

	@Test
	@DisplayName("비정상 투구 테스트 : -1개")
	void invalidPlayTest2() {
		assertThatThrownBy(() -> {
			pins.play(-1);
		}).isInstanceOf(IllegalArgumentException.class).hasMessage("쓰러트릴 핀 갯수는 0개 이하일 수 없습니다.");
	}

}
