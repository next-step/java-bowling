package bowling.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("처음 상태 테스트")
class InitializedTest {

	private static final Initialized INITIALIZED = Initialized.getInstance();

	@Test
	void 처음_상태_동일성_테스트() {
		assertThat(Initialized.getInstance()).isSameAs(Initialized.getInstance());
	}

	@Test
	void 처음_상태는_종료가_아님() {
		assertThat(INITIALIZED.isEnd()).isFalse();
	}

	@Test
	void 처음_상태에서_투구했을_때_점수가_10보다_작으면_핀이_남은_상태로_넘어감() {
		assertThat(INITIALIZED.throwBowl(1)).isEqualTo(new Remain(1));
	}

	@Test
	void 처음_상태에서_투구했을_때_점수가_10이면_스트라이크로_넘어감() {
		assertThat(INITIALIZED.throwBowl(10)).isSameAs(Strike.getInstance());
	}
}