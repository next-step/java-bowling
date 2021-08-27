package bowling.model.play;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.model.Pin;

class FinalPlayTest {

	@Test
	@DisplayName("파이널 플레이를 생성한다.")
	public void createFinalPlay() {
		FinalPlay finalPlay = new FinalPlay();

		assertThat(finalPlay).isEqualTo(new FinalPlay());
	}

	@ParameterizedTest
	@DisplayName("플레이를 하면 결과값이 추가 된다.")
	@CsvSource(value = {"5", "4", "10"})
	public void playGame(int pin) {
		FinalPlay finalPlay = new FinalPlay();
		List<Pin> play = finalPlay.play(new Pin(pin));

		assertThat(play).extracting("pin").containsExactly(pin);
	}

	@Test
	@DisplayName("플레이시 플에이의 핀수를 초과하면 예외가 발생된다.")
	public void checkScore() {
		FinalPlay finalPlay = new FinalPlay();
		finalPlay.play(new Pin(5));
		assertThatThrownBy(() -> finalPlay.play(new Pin(6)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@DisplayName("플레이를 하면 나머지 최종 점수를 알수 있다.")
	@CsvSource(value = {"5,5", "4,4", "10,0"})
	public void findTotalScore(int pin, int reminderScore) {
		FinalPlay finalPlay = new FinalPlay();
		finalPlay.play(new Pin(pin));

		assertThat(finalPlay.findReminderScore()).isEqualTo(reminderScore);
	}

	@Test
	@DisplayName("첫번째 스코어가 10점이면 스트라이크 이다.")
	public void isFirstStrike() {
		FinalPlay finalPlay = new FinalPlay();
		finalPlay.play(new Pin(10));

		assertThat(finalPlay.isFirstStrike()).isTrue();
	}

	@Test
	@DisplayName("2번쨰 볼링 게임까지 10점 미만이면 스트라이크나 스페이가 아니다.")
	public void isNotStrikeOrSpare() {
		FinalPlay finalPlay = new FinalPlay();
		finalPlay.play(new Pin(5));
		finalPlay.play(new Pin(4));

		assertThat(finalPlay.isNotStrikeOrSpare()).isTrue();
	}

	@Test
	@DisplayName("스트라이크 이면 게임이 3회 플레이가 진행된다.")
	public void isGameEndByStrike() {
		FinalPlay finalPlay = new FinalPlay();
		finalPlay.play(new Pin(10));
		finalPlay.play(new Pin(10));
		finalPlay.play(new Pin(10));

		assertThat(finalPlay.isGameEnd()).isTrue();
	}

	@Test
	@DisplayName("스트라이크가 아니고 스페어도 아니면 2회 플레이가 가능하다.")
	public void isGameEndByNotStrike() {
		FinalPlay finalPlay = new FinalPlay();
		finalPlay.play(new Pin(5));
		finalPlay.play(new Pin(4));

		assertThat(finalPlay.isGameEnd()).isTrue();
	}

	@Test
	@DisplayName("스페어 이면  3회 플레이가 가능하다.")
	public void isGameEndBySpare() {
		FinalPlay finalPlay = new FinalPlay();
		finalPlay.play(new Pin(5));
		finalPlay.play(new Pin(5));
		finalPlay.play(new Pin(4));

		assertThat(finalPlay.isGameEnd()).isTrue();
	}

}