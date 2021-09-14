package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void play() {
		Frames frames = new Frames();

		// 1프레임
		frames.bowl(4);
		frames.bowl(6);

		// 2프레임
		frames.bowl(7);
		frames.bowl(2);

		// 3프레임
		frames.bowl(10);

		// 4프레임
		frames.bowl(0);
		frames.bowl(5);

		// 5프레임
		frames.bowl(7);
		frames.bowl(2);

		// 6프레임
		frames.bowl(3);
		frames.bowl(4);

		// 7프레임
		frames.bowl(10);

		// 8프레임
		frames.bowl(8);
		frames.bowl(0);

		// 9프레임
		frames.bowl(8);
		frames.bowl(2);

		// 10프레임
		frames.bowl(3);
		frames.bowl(7);
		frames.bowl(10);

		Player player = new Player("PSD");
		Board board = new Board(player, frames);

		Scores scores = board.value().get(player).scores();
		assertThat(scores.scores()).containsExactly(
			Score.from(17), Score.from(26), Score.from(41), Score.from(46), Score.from(55),
			Score.from(62), Score.from(80), Score.from(88), Score.from(101), Score.from(121)
		);
	}

}
