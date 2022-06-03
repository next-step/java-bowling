package bowling.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.point.Point;
import bowling.score.Score;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("스페어 테스트")
class SpareTest {

	@Test
	void 스페어_동등성_테스트() {
		assertThat(new Spare(1, 9)).isEqualTo(new Spare(1, 9));
	}

	@Test
	void 두_번의_투구의_합이_최댓값_보다_작으면_예외() {
		assertThatIllegalArgumentException().isThrownBy(
			() -> new Spare(1, 1)
		);
	}

	@ParameterizedTest(name = "{displayName} : {arguments}")
	@CsvSource(
		delimiter = ':',
		value = {
			"1:9:true",
			"1:8:false"
		}
	)
	void 두_번의_투구로_스페어가_생성가능한지_확인(int first, int second, boolean result) {

		Point point1 = Point.of(first);
		Point point2 = Point.of(second);

		assertThat(Spare.isConstructible(point1, point2)).isEqualTo(result);
	}

	@Test
	void 스페어는_종료_상태() {
		Spare spare = new Spare(1, 9);
		assertThat(spare.isEnd()).isTrue();
	}

	@Test
	void 스페어에서_투구할_수_없음() {
		Spare spare = new Spare(1, 9);
		assertThatThrownBy(
			() -> spare.throwBowl(1)
		).isExactlyInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void 스페어에서_점수_계산_가능() {
		Spare spare = new Spare(1, 9);

		assertThat(spare.score()).isEqualTo(Score.of(10, 1));
	}

	@Test
	void 스페어에서_한_번_보너스_점수_제공_가능() {
		Spare spare = new Spare(1, 9);
		Score previousScore = Score.spare(Point.max().score());

		assertThat(spare.bonus(previousScore)).isEqualTo(Score.of(11, 0));
	}

	@Test
	void 스페어에서_두_번_보너스_점수_제공_가능() {
		Spare spare = new Spare(1, 9);
		Score previousScore = Score.strike(Point.max().score());

		assertThat(spare.bonus(previousScore)).isEqualTo(Score.of(20, 0));
	}
}