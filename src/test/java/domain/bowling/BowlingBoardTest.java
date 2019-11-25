package domain.bowling;

import domain.states.BowlingPins;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class BowlingBoardTest {

	@Test
	void 프레임의_점수가_연이어_계산된다() {
		// given
		BowlingBoard bowlingBoard = BowlingBoard.newInstance();

		// when
		// 1프레임 - 스트라이크 20
		bowlingBoard.roll(BowlingPins.of(10));

		// 2프레임 - 스페어 35
		bowlingBoard.roll(BowlingPins.of(8));
		bowlingBoard.roll(BowlingPins.of(2));

		// 3프레임 - 미스 44
		bowlingBoard.roll(BowlingPins.of(5));
		bowlingBoard.roll(BowlingPins.of(4));

		// 4프레임 - 거터 44
		bowlingBoard.roll(BowlingPins.of(0));
		bowlingBoard.roll(BowlingPins.of(0));

		// 5프레임 - 스트라이크 71 (64, 1)
		bowlingBoard.roll(BowlingPins.of(10));

		// 6프레임 - 스트라이크 91 (74, 3) -> 7이 두 번 더해져야 하는데 7 3 2가 더해져버림
		bowlingBoard.roll(BowlingPins.of(10));

		// 7프레임 - 스페어 103
		bowlingBoard.roll(BowlingPins.of(7));
		bowlingBoard.roll(BowlingPins.of(3));

		// 7프레임 - 미스
		bowlingBoard.roll(BowlingPins.of(2));
		bowlingBoard.roll(BowlingPins.of(1));

		// 7프레임 - 미스
		bowlingBoard.roll(BowlingPins.of(4));
		bowlingBoard.roll(BowlingPins.of(3));

		// 10프레임 - 10 10 10
		bowlingBoard.roll(BowlingPins.of(10));
		bowlingBoard.roll(BowlingPins.of(10));
		bowlingBoard.roll(BowlingPins.of(10));

		// then
		assertThat(bowlingBoard.getScores()).isEqualTo(Arrays.asList(Optional.of(20),
				Optional.of(35),
				Optional.of(44),
				Optional.of(44),
				Optional.of(71),
				Optional.of(91),
				Optional.of(103),
				Optional.of(106),
				Optional.of(113),
				Optional.of(143)));
	}

}
