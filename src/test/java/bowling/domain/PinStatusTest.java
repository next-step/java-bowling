package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PinStatusTest {

	private PinStatus pinStatus;

	@BeforeEach
	void setUp() {
		pinStatus = new PinStatus();
	}

	@Test
	@DisplayName("투구별 쓰러트린 핀 갯수 테스트 1")
	void pinStatusTest() {
		pinStatus.add(4);
		pinStatus.add(5);
		assertThat(pinStatus.firstPin()).isEqualTo(4);
		assertThat(pinStatus.secondPin()).isEqualTo(5);
		assertThat(pinStatus.totalPin()).isEqualTo(9);
	}

	@Test
	@DisplayName("투구별 쓰러트린 핀 갯수 테스트 2")
	void pinStatusTest2() {
		pinStatus.add(10);
		assertThat(pinStatus.firstPin()).isEqualTo(10);
		assertThat(pinStatus.totalPin()).isEqualTo(10);
	}

	@Test
	@DisplayName("스트라이크 테스트")
	void strikeTest() {
		pinStatus.add(10);
		assertThat(pinStatus.firstPin()).isEqualTo(10);
	}

	@Test
	@DisplayName("스페어 처리 : 첫투구가 0이고, 이후 스페어처리")
	void spareTest() {
		pinStatus.add(0);
		pinStatus.add(10);
		assertThat(pinStatus.firstPin()).isEqualTo(0);
		assertThat(pinStatus.secondPin()).isEqualTo(10);
	}

	@Test
	@DisplayName("스페어 처리2 : 첫투구가 0이 아니고, 이후 스페어처리")
	void spareTest2() {
		pinStatus.add(5);
		pinStatus.add(5);
		assertThat(pinStatus.firstPin()).isEqualTo(5);
		assertThat(pinStatus.secondPin()).isEqualTo(5);
		assertThat(Score.score(pinStatus.secondPin(), pinStatus.firstPin(), 2)).isEqualTo(Score.SPARE);
	}

	@Test
	@DisplayName("MISS 테스트: 첫투구가 0이 아니면서, 스페어처리 못함")
	void missTest() {
		pinStatus.add(5);
		pinStatus.add(3);
		assertThat(pinStatus.firstPin()).isEqualTo(5);
		assertThat(pinStatus.secondPin()).isEqualTo(3);
		assertThat(Score.score(pinStatus.secondPin(), pinStatus.firstPin(), 2)).isEqualTo(Score.MISS);
	}

	@Test
	@DisplayName("MISS 테스트2 : 첫투구가 0이면서, 이후 스페어처리 못함")
	void missTest2() {
		pinStatus.add(0);
		pinStatus.add(9);
		assertThat(pinStatus.firstPin()).isEqualTo(0);
		assertThat(pinStatus.secondPin()).isEqualTo(9);
		assertThat(Score.score(pinStatus.secondPin(), pinStatus.firstPin(), 2)).isEqualTo(Score.MISS);
	}

	@Test
	@DisplayName("Gutter 테스트")
	void gutterTest() {
		pinStatus.add(0);
		pinStatus.add(0);
		assertThat(pinStatus.firstPin()).isEqualTo(0);
		assertThat(pinStatus.secondPin()).isEqualTo(0);
		assertThat(Score.score(pinStatus.secondPin(), pinStatus.firstPin(), 2)).isEqualTo(Score.GUTTER);
	}

}
