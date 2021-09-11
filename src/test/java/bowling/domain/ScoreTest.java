package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

	@DisplayName("쓰러뜨린 볼링 핀의 수만큼 점수를 계산한다.")
	@Test
	void create() {
		Score score = Score.of(5);
		assertThat(score).isEqualTo(Score.of(5));
	}

	@DisplayName("두 점수를 더하면 합한 점수가 생성된다.")
	@Test
	void add() {
		Score score = Score.of(5);
		Score additionalScore = Score.of(3);
		assertThat(score.add(additionalScore)).isEqualTo(Score.of(8));
	}

}
