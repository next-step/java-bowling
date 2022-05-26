package bowling.record;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("투구 기록 테스트")
class PointTest {

	@Test
	void 투구_기록을_Wrappinp_하는_객체를_생성() {
		Point actual = new Point(1);
		Point expected = new Point(1);

		assertThat(actual).isEqualTo(expected);
	}

	@ParameterizedTest(name = "{displayName} : {arguments}")
	@ValueSource(ints = {-1, 11})
	void 투구_기록이_범위_안에_있지_않으면_예외(int throwCount) {
		assertThatIllegalArgumentException().isThrownBy(() -> new Point(throwCount));
	}
}