package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.score.Score;

public class FinalFrameTest {

	public static Stream<Arguments> 마지막_프레임에서_스트라이크인_경우() {
		Frame frameStrike = FinalFrame.of();
		frameStrike.addFirstScore(Score.ofScore(10));
		boolean canAddMoreScoreForFrameStrike = frameStrike.canPlayMore();

		Frame frameNotStrike = FinalFrame.of();
		frameNotStrike.addFirstScore(Score.ofScore(7));
		frameNotStrike.addSecondScore(Score.ofScore(1));
		boolean canAddMoreScoreForFrameNotStrike = frameNotStrike.canPlayMore();

		return Stream.of(
			Arguments.of(canAddMoreScoreForFrameStrike, true),
			Arguments.of(canAddMoreScoreForFrameNotStrike, false)
		);
	}

	public static Stream<Arguments> 마지막_프레임에서_스페어인_경우() {
		Frame frame = FinalFrame.of();
		frame.addFirstScore(Score.ofScore(1));
		boolean canAddMoreScore1 = frame.canPlayMore();

		frame.addSecondScore(Score.ofScore(9));
		boolean canAddMoreScore2 = frame.canPlayMore();

		frame.addBonusScore(Score.ofScore(1));
		boolean canAddMoreScore3 = frame.canPlayMore();

		return Stream.of(
			Arguments.of(canAddMoreScore1, true),
			Arguments.of(canAddMoreScore2, true),
			Arguments.of(canAddMoreScore3, false));
	}

	@DisplayName("마지막 프레임의 첫 번째 투구에서 스트라이크를 기록하면 더 플레이할 수 있다.")
	@MethodSource("마지막_프레임에서_스트라이크인_경우")
	@ParameterizedTest
	void 마지막_프레임이_스트라이크라_플레이를_추가한다(boolean result, boolean expected) {
		assertThat(result).isEqualTo(expected);
	}

	@DisplayName("마지막 프레임의 두 번째 투구에서 스페어를 기록하면 더 플레이할 수 있다.")
	@MethodSource("마지막_프레임에서_스페어인_경우")
	@ParameterizedTest
	void 마지막_프레임이_스페어라_플레이를_추가한다(boolean result, boolean expected) {
		assertThat(result).isEqualTo(expected);
	}
}
