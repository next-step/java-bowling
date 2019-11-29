package domain.bowling;

import domain.BowlingPins;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class BowlingBoardTest {

	@Test
	void 프레임의_점수가_연이어_계산된다1() {
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

		// 5프레임 - 스트라이크 71
		bowlingBoard.roll(BowlingPins.of(10));

		// 6프레임 - 스트라이크 91
		bowlingBoard.roll(BowlingPins.of(10));

		// 7프레임 - 스페어 103
		bowlingBoard.roll(BowlingPins.of(7));
		bowlingBoard.roll(BowlingPins.of(3));

		// 7프레임 - 미스 106
		bowlingBoard.roll(BowlingPins.of(2));
		bowlingBoard.roll(BowlingPins.of(1));

		// 7프레임 - 미스 113
		bowlingBoard.roll(BowlingPins.of(4));
		bowlingBoard.roll(BowlingPins.of(3));

		// 10프레임 - 30점 획득 143
		bowlingBoard.roll(BowlingPins.of(10));
		bowlingBoard.roll(BowlingPins.of(10));
		bowlingBoard.roll(BowlingPins.of(10));

		// then
		assertThat(bowlingBoard.getScores()).isEqualTo(Arrays.asList(
				Optional.of(20),
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

	@Test
	void 프레임의_점수가_연이어_계산된다2() {
		// given
		BowlingBoard bowlingBoard = BowlingBoard.newInstance();

		// when
		// 1프레임 - 스페어 20
		bowlingBoard.roll(BowlingPins.of(4));
		bowlingBoard.roll(BowlingPins.of(6));

		// 2프레임 - 스트라이크 50
		bowlingBoard.roll(BowlingPins.of(10));

		// 3프레임 - 스트라이크 74
		bowlingBoard.roll(BowlingPins.of(10));

		// 4프레임 - 스트라이크 91
		bowlingBoard.roll(BowlingPins.of(10));

		// 5프레임 - 미스 98
		bowlingBoard.roll(BowlingPins.of(4));
		bowlingBoard.roll(BowlingPins.of(3));

		// 6프레임 - 미스 105
		bowlingBoard.roll(BowlingPins.of(4));
		bowlingBoard.roll(BowlingPins.of(3));

		// 7프레임 - 미스 112
		bowlingBoard.roll(BowlingPins.of(4));
		bowlingBoard.roll(BowlingPins.of(3));

		// 8프레임 - 미스 115
		bowlingBoard.roll(BowlingPins.of(2));
		bowlingBoard.roll(BowlingPins.of(1));

		// 9프레임 - 미스 122
		bowlingBoard.roll(BowlingPins.of(4));
		bowlingBoard.roll(BowlingPins.of(3));

		// 10프레임 - 9점 획득 131
		bowlingBoard.roll(BowlingPins.of(6));
		bowlingBoard.roll(BowlingPins.of(3));

		// then
		assertThat(bowlingBoard.getScores()).isEqualTo(Arrays.asList(
				Optional.of(20),
				Optional.of(50),
				Optional.of(74),
				Optional.of(91),
				Optional.of(98),
				Optional.of(105),
				Optional.of(112),
				Optional.of(115),
				Optional.of(122),
				Optional.of(131)));
	}

}
