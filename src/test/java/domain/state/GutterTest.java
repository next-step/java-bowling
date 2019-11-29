package domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class GutterTest {

	@Test
	void GUTTER의_다음_상태는_없다() {
		// given
		Gutter gutter = Gutter.getInstance();

		// when & then
		assertThatThrownBy(() -> {
			gutter.nextState(0);
		})
		.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void GUTTER는_출력하면_항상_0이다() {
		// given
		Gutter gutter = Gutter.getInstance();

		// when
		assertThat(gutter.toSign()).isEqualTo("0");
	}

}
