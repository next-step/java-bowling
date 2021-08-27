package bowling.model.play;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.model.Pin;

class NormalPlayTest {

	@Test
	@DisplayName("노멀플레이를 생성한다.")
	public void createNormalPlay() {
		NormalPlay normalPlay = new NormalPlay();

		assertThat(normalPlay).isEqualTo(new NormalPlay());
	}

	@ParameterizedTest
	@DisplayName("플레이를 하면 결과값이 추가 된다.")
	@CsvSource(value = {"5", "4", "10"})
	public void playGame(int pin) {
		NormalPlay normalPlay = new NormalPlay();
		List<Pin> play = normalPlay.play(new Pin(pin));

		assertThat(play).extracting("pin").containsExactly(pin);
	}

	@Test
	@DisplayName("플레이시 플에이의 핀수를 초과하면 예외가 발생된다.")
	public void checkScore() {
		NormalPlay normalPlay = new NormalPlay();
		normalPlay.play(new Pin(5));
		assertThatThrownBy(() -> normalPlay.play(new Pin(6)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@DisplayName("플레이를 하면 최종 점수를 알수 있다.")
	@CsvSource(value = {"5", "4", "10"})
	public void findTotalScore(int pin) {
		NormalPlay normalPlay = new NormalPlay();
		normalPlay.play(new Pin(pin));

		assertThat(normalPlay.findTotalScore()).isEqualTo(pin);
	}

	@Test
	@DisplayName("첫번째 스코어가 10점이면 스트라이크 이다.")
	public void isFirstStrike() {
		NormalPlay normalPlay = new NormalPlay();
		normalPlay.play(new Pin(10));

		assertThat(normalPlay.isFirstStrike()).isTrue();
	}

	@Test
	@DisplayName("2번쨰 볼링 게임까지 10점 미만이면 스트라이크나 스페이가 아니다.")
	public void isNotStrikeOrSpare() {
		NormalPlay normalPlay = new NormalPlay();
		normalPlay.play(new Pin(5));
		normalPlay.play(new Pin(4));

		assertThat(normalPlay.isNotStrikeOrSpare()).isTrue();
	}

	@Test
	@DisplayName("스트라이크 이면 게임이 종료된다.")
	public void isGameEndByStrike() {
		NormalPlay normalPlay = new NormalPlay();
		normalPlay.play(new Pin(10));

		assertThat(normalPlay.isGameEnd()).isTrue();
	}

	@Test
	@DisplayName("스트라이크가 아니면 2번째에 게임이 종료된다.")
	public void isGameEndByNotStrike() {
		NormalPlay normalPlay = new NormalPlay();
		normalPlay.play(new Pin(5));
		normalPlay.play(new Pin(4));

		assertThat(normalPlay.isGameEnd()).isTrue();
	}

}