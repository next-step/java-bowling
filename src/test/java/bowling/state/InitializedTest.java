package bowling.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("첫 시도 테스트")
class InitializedTest {

	@Test
	void 시작_상태는_종료가_아님() {
		assertThat(new Initialized().isEnd()).isFalse();
	}

	@Test
	void 투구가_스트라이크가_아니면_다음기회로_넘어감() {
		State initialized = new Initialized();

		assertThat(initialized.throwBowl(1)).isEqualTo(new Remain(1));
	}

	@Test
	void 투구가_스트라이크이면_스트라이크로_넘어감() {
		State initialized = new Initialized();

		assertThat(initialized.throwBowl(10)).isSameAs(Strike.INSTANCE);
	}
}