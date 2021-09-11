package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoresTest {

	@Test
	void scores() {
		Frames frames = new Frames();
		frames.bowl(10);
		frames.bowl(3);
		frames.bowl(4);

		Scores scores = Scores.of(frames);
		assertThat(scores.scoreOf(1)).isEqualTo(Score.of(17));
		assertThat(scores.scoreOf(2)).isEqualTo(Score.of(24));
	}

}
