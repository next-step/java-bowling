package domain.bowling;

import domain.bowlling.BowlingBoard;
import domain.phase.result.FinalPhaseResult;
import domain.phase.result.NormalPhaseResult;
import org.junit.jupiter.api.Test;

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
		assertThat(board.getPhaseResults().get(0)).containsExactly(NormalPhaseResult.STRIKE);
	}

	@Test
	void 노말_프레임에서_스페어를_하면_결과가_저장되고_다음_페이즈_인덱스가_반환된다() {
		// given
		BowlingBoard board = new BowlingBoard();

		// when
		int frameIndex = board.shoot(0, 4);
		int nextFrameIndex = board.shoot(frameIndex, 6);

		// then
		assertThat(nextFrameIndex).isEqualTo(1);
		assertThat(board.getPhaseResults().get(0)).containsExactly(NormalPhaseResult.IN_PROGRESS,
				NormalPhaseResult.SPARE);
	}

	@Test
	void 노말_프레임에서_미스를_하면_결과가_저장되고_다음_페이즈_인덱스가_반환된다() {
		// given
		BowlingBoard board = new BowlingBoard();

		// when
		int frameIndex = board.shoot(0, 4);
		int nextFrameIndex = board.shoot(frameIndex, 4);

		// then
		assertThat(nextFrameIndex).isEqualTo(1);
		assertThat(board.getPhaseResults().get(0)).containsExactly(NormalPhaseResult.IN_PROGRESS,
				NormalPhaseResult.MISS);
	}

	@Test
	void 노말_프레임에서_거터를_하면_결과가_저장되고_다음_페이즈_인덱스가_반환된다() {
		// given
		BowlingBoard board = new BowlingBoard();

		// when
		int frameIndex = board.shoot(0, 0);
		int nextFrameIndex = board.shoot(frameIndex, 0);

		// then
		assertThat(nextFrameIndex).isEqualTo(1);
		assertThat(board.getPhaseResults().get(0)).containsExactly(NormalPhaseResult.IN_PROGRESS,
				NormalPhaseResult.GUTTER);
	}

	@Test
	void 마지막_프레임에서_스트라이크를_하면_두_번을_추가로칠_수_있다() {
		// given
		BowlingBoard board = new BowlingBoard();

		// when
		int frameIndex1 = board.shoot(9, 10);
		int frameIndex2 = board.shoot(frameIndex1, 0);
		int nextFrameIndex = board.shoot(frameIndex2, 4);

		// then
		assertThat(nextFrameIndex).isEqualTo(10);
		assertThat(board.getPhaseResults().get(9)).containsExactly(FinalPhaseResult.STRIKE,
				FinalPhaseResult.GUTTER, FinalPhaseResult.LAST_SCORE);
	}


	@Test
	void 마지막_프레임에서_미스를_내면_끝나게_된다() {
		// given
		BowlingBoard board = new BowlingBoard();

		// when
		int frameIndex1 = board.shoot(9, 3);
		int nextFrameIndex = board.shoot(frameIndex1, 4);

		// then
		assertThat(nextFrameIndex).isEqualTo(10);
		assertThat(board.getPhaseResults().get(9)).containsExactly(FinalPhaseResult.IN_PROGRESS,
				FinalPhaseResult.MISS);
	}

	@Test
	void 마지막_프레임에서_거터를_내면_끝나게_된다() {
		// given
		BowlingBoard board = new BowlingBoard();

		// when
		int frameIndex1 = board.shoot(9, 0);
		int nextFrameIndex = board.shoot(frameIndex1, 0);

		// then
		assertThat(nextFrameIndex).isEqualTo(10);
		assertThat(board.getPhaseResults().get(9)).containsExactly(FinalPhaseResult.IN_PROGRESS,
				FinalPhaseResult.GUTTER);

	}

}
