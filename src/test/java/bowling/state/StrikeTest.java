package bowling.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.point.Point;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("스트라이크 테스트")
class StrikeTest {

	@Test
	void 스트라이크_동일성_테스트() {
		assertThat(Strike.getInstance()).isSameAs(Strike.getInstance());
	}

	@ParameterizedTest(name = "{displayName} : {arguments}")
	@CsvSource(
		delimiter = ':',
		value = {
			"9:false",
			"10:true"
		}
	)
	void 투구가_스트라이크인지_확인(int value, boolean result) {
		Point point = Point.of(value);

		assertThat(Strike.isConstructible(point)).isEqualTo(result);
	}

	@Test
	void 스트라이크는_종료_상태() {
		assertThat(Strike.getInstance().isEnd()).isTrue();
	}

	@Test
	void 스트라이크_상태에서_투구할_수_없음() {
		Strike strike = Strike.getInstance();
		assertThatThrownBy(
			() -> strike.throwBowl(1)
		).isExactlyInstanceOf(UnsupportedOperationException.class);
	}

}