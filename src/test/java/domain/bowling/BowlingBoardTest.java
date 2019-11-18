package domain.bowling;

import domain.bowlling.BowlingBoard;
import domain.frame.result.FrameResult;
import domain.phase.result.FinalPhaseResult;
import domain.phase.result.NormalPhaseResult;
import domain.phase.result.PhaseResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class BowlingBoardTest {

	@Test
	void 노말_프레임에서_스트라이크를_하면_결과가_저장되고_다음_페이즈_인덱스가_반환된다() {
		// given
		BowlingBoard board = new BowlingBoard();

		// when
		int nextFrameIndex = board.shoot(0, 10);

		// then
		assertThat(nextFrameIndex).isEqualTo(1);
		assertThat(board.getPhaseResults().get(0)).isEqualTo(new FrameResult(NormalPhaseResult.STRIKE, 10));
	}

	@Test
	void 노말_프레임에서_스페어를_하면_결과가_저장되고_다음_페이즈_인덱스가_반환된다() {
		// given
		BowlingBoard board = new BowlingBoard();
		List<PhaseResult> expectedResults = Arrays.asList(NormalPhaseResult.IN_PROGRESS,
				NormalPhaseResult.SPARE);
		List<Integer> expectedFallenBowlingPins = Arrays.asList(4, 6);

		// when
		int frameIndex = board.shoot(0, 4);
		int nextFrameIndex = board.shoot(frameIndex, 6);

		// then
		assertThat(nextFrameIndex).isEqualTo(1);
		assertThat(board.getPhaseResults().get(0)).isEqualTo(new FrameResult(expectedResults, expectedFallenBowlingPins));
	}

	@Test
	void 노말_프레임에서_미스를_하면_결과가_저장되고_다음_페이즈_인덱스가_반환된다() {
		// given
		BowlingBoard board = new BowlingBoard();
		List<PhaseResult> expectedResults = Arrays.asList(NormalPhaseResult.IN_PROGRESS,
				NormalPhaseResult.MISS);
		List<Integer> expectedFallenBowlingPins = Arrays.asList(4, 4);

		// when
		int frameIndex = board.shoot(0, 4);
		int nextFrameIndex = board.shoot(frameIndex, 4);

		// then
		assertThat(nextFrameIndex).isEqualTo(1);
		assertThat(board.getPhaseResults().get(0)).isEqualTo(new FrameResult(expectedResults, expectedFallenBowlingPins));
	}

	@Test
	void 노말_프레임에서_거터를_하면_결과가_저장되고_다음_페이즈_인덱스가_반환된다() {
		// given
		BowlingBoard board = new BowlingBoard();
		List<PhaseResult> expectedResults = Arrays.asList(NormalPhaseResult.IN_PROGRESS,
				NormalPhaseResult.GUTTER);
		List<Integer> expectedFallenBowlingPins = Arrays.asList(0, 0);

		// when
		int frameIndex = board.shoot(0, 0);
		int nextFrameIndex = board.shoot(frameIndex, 0);

		// then
		assertThat(nextFrameIndex).isEqualTo(1);
		assertThat(board.getPhaseResults().get(0)).isEqualTo(new FrameResult(expectedResults, expectedFallenBowlingPins));
	}

	@Test
	void 마지막_프레임에서_스트라이크를_하면_두_번을_추가로칠_수_있다() {
		// given
		BowlingBoard board = new BowlingBoard();
		List<PhaseResult> expectedResults = Arrays.asList(FinalPhaseResult.STRIKE,
				FinalPhaseResult.GUTTER, FinalPhaseResult.LAST_SCORE);
		List<Integer> expectedFallenBowlingPins = Arrays.asList(10, 0, 4);

		// when
		int frameIndex1 = board.shoot(9, 10);
		int frameIndex2 = board.shoot(frameIndex1, 0);
		int nextFrameIndex = board.shoot(frameIndex2, 4);

		// then
		assertThat(nextFrameIndex).isEqualTo(10);
		assertThat(board.getPhaseResults().get(9)).isEqualTo(new FrameResult(expectedResults, expectedFallenBowlingPins));
	}


	@Test
	void 마지막_프레임에서_미스를_내면_끝나게_된다() {
		// given
		BowlingBoard board = new BowlingBoard();
		List<PhaseResult> expectedResults = Arrays.asList(FinalPhaseResult.IN_PROGRESS,
				FinalPhaseResult.MISS);
		List<Integer> expectedFallenBowlingPins = Arrays.asList(3, 4);

		// when
		int frameIndex1 = board.shoot(9, 3);
		int nextFrameIndex = board.shoot(frameIndex1, 4);

		// then
		assertThat(nextFrameIndex).isEqualTo(10);
		assertThat(board.getPhaseResults().get(9)).isEqualTo(new FrameResult(expectedResults, expectedFallenBowlingPins));
	}

	@Test
	void 마지막_프레임에서_거터를_내면_끝나게_된다() {
		// given
		BowlingBoard board = new BowlingBoard();
		List<PhaseResult> expectedResults = Arrays.asList(FinalPhaseResult.IN_PROGRESS,
				FinalPhaseResult.GUTTER);
		List<Integer> expectedFallenBowlingPins = Arrays.asList(0, 0);

		// when
		int frameIndex1 = board.shoot(9, 0);
		int nextFrameIndex = board.shoot(frameIndex1, 0);

		// then
		assertThat(nextFrameIndex).isEqualTo(10);
		assertThat(board.getPhaseResults().get(9)).isEqualTo(new FrameResult(expectedResults, expectedFallenBowlingPins));

	}

}
