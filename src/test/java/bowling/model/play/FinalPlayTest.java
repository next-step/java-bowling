package bowling.model.play;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.model.Pin;

class FinalPlayTest {

	@Test
	@DisplayName("플레이를 생성한다.")
	public void createPlay() {
		FinalPlay finalPlay = new FinalPlay(10);

		assertThat(finalPlay).isEqualTo(new FinalPlay(10));
	}

	@Test
	@DisplayName("플레이 이후 해당 번째 스코어를 찾을 수 있다.")
	public void findPin() {
		FinalPlay finalPlay = new FinalPlay(10);
		finalPlay.play(new Pin(5));
		finalPlay.play(new Pin(4));

		assertAll(
			() -> assertThat(finalPlay.findPin(0)).isEqualTo(new Pin(5)),
			() -> assertThat(finalPlay.findPin(1)).isEqualTo(new Pin(4))
		);
	}

	@Test
	@DisplayName("스트라이크가 아니고 핀의 합이 10 초과이면 예외가 발생된다.")
	public void checkScore() {
		FinalPlay finalPlay = new FinalPlay(10);
		finalPlay.play(new Pin(5));

		assertThatThrownBy(() -> finalPlay.play(new Pin(6)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("첫번째 볼이 10이면 스트라이크 이다.")
	public void isStrike() {
		FinalPlay finalPlay = new FinalPlay(10);
		finalPlay.play(new Pin(10));

		assertThat(finalPlay.isStrike()).isTrue();
	}

	@Test
	@DisplayName("스트라이크가 아니고 2번째볼과의 합이 10이면 스페어 이다.")
	public void isSpare() {
		FinalPlay finalPlay = new FinalPlay(10);
		finalPlay.play(new Pin(4));
		finalPlay.play(new Pin(6));

		assertThat(finalPlay.isSpare()).isTrue();
	}

	@Test
	@DisplayName("스트라이크도 아니고 스페어도아니면 miss 이다.")
	public void isMiss() {
		FinalPlay finalPlay = new FinalPlay(1);
		finalPlay.play(new Pin(4));
		finalPlay.play(new Pin(4));

		assertThat(finalPlay.isMiss()).isTrue();
	}

	@Test
	@DisplayName("마지막 프레임은 3번 플레이하거나 미스이면 종료이다.")
	public void isGameEndByFinalFrame() {
		FinalPlay finalPlay = new FinalPlay(10);
		finalPlay.play(new Pin(10));
		finalPlay.play(new Pin(5));
		finalPlay.play(new Pin(4));

		FinalPlay finalPlay2 = new FinalPlay(1);
		finalPlay2.play(new Pin(4));
		finalPlay2.play(new Pin(5));

		assertAll(
			() -> assertThat(finalPlay.isGameEnd()).isTrue(),
			() -> assertThat(finalPlay2.isGameEnd()).isTrue()
		);
	}

}