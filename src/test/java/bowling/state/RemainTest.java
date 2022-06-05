package bowling.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import bowling.point.Point;
import bowling.score.Score;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("핀이 남은 상태 테스트")
class RemainTest {

	@Test
	void 핀이_남은_상태_동등성_테스트() {
		assertThat(new Remain(1)).isEqualTo(new Remain(1));
	}

	@Test
	void 핀이_남은_상태에서는_갖고있는_점수가_10점_일_수_없음() {
		assertThatIllegalArgumentException().isThrownBy(
			() -> new Remain(10)
		);
	}

	@Test
	void 핀이_남은_상태는_종료_상태가_아님() {
		assertThat(new Remain(1).isEnd()).isFalse();
	}

	@Test
	void 핀이_남은_상태에서_투구했을_때_핀이_모두_넘어가면_스페어() {
		Remain remain = new Remain(1);

		assertThat(remain.throwBowl(9)).isEqualTo(new Spare(1, 9));
	}

	@Test
	void 핀이_남은_상태에서_투구했을_때_핀이_모두_넘어가지_않으면_오픈() {
		Remain remain = new Remain(1);

		assertThat(remain.throwBowl(8)).isEqualTo(new Open(1, 8));
	}

	@Test
	void 핀이_남은_상태는_점수_계산_가능() {
		Remain remain = new Remain(1);
		assertThat(remain.score()).isEqualTo(Score.of(1, 0));
	}

	@Test
	void 핀이_남은_상태는_보너스_횟수를_차감하고_점수를_제공함() {
		Remain remain = new Remain(1);
		Score strike = Score.strike(Point.max().score());

		assertThat(remain.bonus(strike)).isEqualTo(Score.of(11, 1));
	}

}