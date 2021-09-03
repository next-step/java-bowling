package bowling.model.play;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.model.Pin;
import bowling.model.score.Score;

class PlayTest {

	@Test
	@DisplayName("플레이를 생성한다.")
	public void createPlay() {
		Play play = new Play(1);

		assertThat(play).isEqualTo(new Play(1));
	}

	@Test
	@DisplayName("플레이 이후 해당 번째 스코어를 찾을 수 있다.")
	public void findPin() {
		Play play = new Play(1);
		play.play(new Pin(5));
		play.play(new Pin(4));

		assertAll(
			() -> assertThat(play.findPin(0)).isEqualTo(new Pin(5)),
			() -> assertThat(play.findPin(1)).isEqualTo(new Pin(4))
		);
	}

	@Test
	@DisplayName("스트라이크가 아니고 핀의 합이 10 초과이면 예외가 발생된다.")
	public void checkScore() {
		Play play = new Play(1);
		play.play(new Pin(5));

		assertThatThrownBy(() -> play.play(new Pin(6)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("첫번째 볼이 10이면 스트라이크 이다.")
	public void isStrike() {
		Play play = new Play(1);
		play.play(new Pin(10));

		assertThat(play.isStrike()).isTrue();
	}

	@Test
	@DisplayName("스트라이크가 아니고 2번째볼과의 합이 10이면 스페어 이다.")
	public void isSpare() {
		Play play = new Play(1);
		play.play(new Pin(4));
		play.play(new Pin(6));

		assertThat(play.isSpare()).isTrue();
	}

	@Test
	@DisplayName("스트라이크도 아니고 스페어도아니면 miss 이다.")
	public void isMiss() {
		Play play = new Play(1);
		play.play(new Pin(4));
		play.play(new Pin(4));

		assertThat(play.isMiss()).isTrue();
	}

	@Test
	@DisplayName("노멀 프레임은 스트라이크이거나 2번 play 하면 게임 종료이다.")
	public void isGameEndByNormalFrame() {
		Play play = new Play(1);
		play.play(new Pin(10));

		Play play2 = new Play(1);
		play2.play(new Pin(4));
		play2.play(new Pin(5));

		assertAll(
			() -> assertThat(play.isGameEnd()).isTrue(),
			() -> assertThat(play2.isGameEnd()).isTrue()
		);
	}

	@Test
	@DisplayName("마지막 프레임은 3번 플레이하거나 미스이면 종료이다.")
	public void isGameEndByFinalFrame() {
		Play play = new Play(1);
		play.play(new Pin(10));
		play.play(new Pin(5));
		play.play(new Pin(4));

		Play play2 = new Play(1);
		play2.play(new Pin(4));
		play2.play(new Pin(5));

		assertAll(
			() -> assertThat(play.isGameEnd()).isTrue(),
			() -> assertThat(play2.isGameEnd()).isTrue()
		);
	}

	@Test
	@DisplayName("play 가 스트라이크 이면 Score 가 strike 이다.")
	public void playToScoreByStrike() {
		Play play = new Play(1);
		Score score = play.play(new Pin(10));

		assertThat(score).isEqualTo(Score.strike(getPins(10)));
	}

	@Test
	@DisplayName("play 가 스페어 이면 Score 가 spare 이다.")
	public void playToScoreBySpare() {
		Play play = new Play(1);
		play.play(new Pin(4));
		Score score = play.play(new Pin(6));

		assertThat(score).isEqualTo(Score.spare(Arrays.asList(new Pin(4), new Pin(6))));
	}

	@Test
	@DisplayName("play 가 스페어나 스트라이크가 아니면 Score 가 miss 이다.")
	public void playToScoreByMiss() {
		Play play = new Play(1);
		play.play(new Pin(4));
		Score score = play.play(new Pin(4));

		assertThat(score).isEqualTo(Score.miss(Arrays.asList(new Pin(4), new Pin(4)), 8));
	}

	@Test
	@DisplayName("play 가 완료가 아니면 Score 가 nothing 이다.")
	public void playToScoreByNothing() {
		Play play = new Play(1);
		Score score = play.play(new Pin(4));

		assertThat(score).isEqualTo(Score.nothing(getPins(4)));
	}

	private List<Pin> getPins(int pin) {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(pin));
		return pins;
	}
}