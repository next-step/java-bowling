package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoresTest {

	@DisplayName("점수 계산 - 스트라이크 포함")
	@Test
	void strikeBonus() {
		Frames frames = new Frames();
		frames.bowl(10);
		frames.bowl(3);
		frames.bowl(4);

		Scores scores = Scores.from(frames);
		assertThat(scores.scoreOf(1).value()).isEqualTo(Score.from(17).value());
		assertThat(scores.scoreOf(2).value()).isEqualTo(Score.from(24).value());
	}

	@DisplayName("점수 계산 - 스페어 포함")
	@Test
	void spareBonus() {
		Frames frames = new Frames();
		frames.bowl(7);
		frames.bowl(3);
		frames.bowl(5);
		frames.bowl(4);

		Scores scores = Scores.from(frames);
		assertThat(scores.scoreOf(1)).isEqualTo(Score.from(15));
		assertThat(scores.scoreOf(2)).isEqualTo(Score.from(24));
	}
	
	@DisplayName("점수 계산 - 미스")
	@Test
	void miss() {
		Frames frames = new Frames();
		frames.bowl(4);
		frames.bowl(5);

		Scores scores = Scores.from(frames);
		assertThat(scores.scoreOf(1)).isEqualTo(Score.from(9));
	}

}
