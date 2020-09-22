package bowling.domain.score;

import bowling.domain.DownedPinCount;
import bowling.domain.score.normalframe.OpenScore;
import bowling.domain.score.normalframe.PlayingScore;
import bowling.domain.score.normalframe.SpareScore;
import bowling.domain.score.normalframe.StrikeScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

	private Score ground;
	private Score playing;
	private Score strike;
	private Score spare;
	private Score open;

	@BeforeEach
	void setup() {
		ground = GroundScore.getInstance();
		playing = new PlayingScore(DownedPinCount.NO_PIN_DOWN, ground);
		strike = new StrikeScore(ground);
		spare = new SpareScore(DownedPinCount.fromDownCount(5), DownedPinCount.fromDownCount(5), ground);
		open = new OpenScore(DownedPinCount.fromDownCount(5), DownedPinCount.fromDownCount(4), ground);
	}

	@DisplayName("init 객체 싱글턴 검증 테스트")
	@Test
	void initialize() {
		assertThat(ground).isEqualTo(GroundScore.getInstance());
	}

	@DisplayName("추가 투구를 더해서 점수계산하는 Score 객체가 알맞게 세팅 되어있나 테스트")
	@Test
	void isRemainExtra() {
		assertThat(ground.isRemainExtra()).isFalse();
		assertThat(playing.isRemainExtra()).isFalse();
		assertThat(strike.isRemainExtra()).isTrue();
		assertThat(spare.isRemainExtra()).isTrue();
		assertThat(open.isRemainExtra()).isFalse();
	}

	@DisplayName("Strike , Spare가 정해진 추가 투구를 수행하고 나면 추가 투구가 불가능해지나 테스트")
	@Test
	void addExtraCount() {
		strike.addExtraCount(DownedPinCount.ALL_PIN_DOWN);
		assertThat(strike.isRemainExtra()).isTrue();
		strike.addExtraCount(DownedPinCount.ALL_PIN_DOWN);
		assertThat(strike.isRemainExtra()).isFalse();
		spare.addExtraCount(DownedPinCount.ALL_PIN_DOWN);
		assertThat(spare.isRemainExtra()).isFalse();
	}

	@DisplayName("strike, spare는 보너스 투구가 끝날 때 까지 0으로 나오고 그 이후엔 추가 투구까지 더한 값을 반환, 나머지는 추가투구를 해도 값이 변하지 않음 ")
	@Test
	void getValue() {
		strike.addExtraCount(DownedPinCount.ALL_PIN_DOWN);
		assertThat(strike.getValue().intValue()).isEqualTo(0);
		strike.addExtraCount(DownedPinCount.ALL_PIN_DOWN);
		assertThat(strike.getValue().intValue()).isEqualTo(30);
		assertThat(spare.getValue().intValue()).isEqualTo(0);
		spare.addExtraCount(DownedPinCount.ALL_PIN_DOWN);
		assertThat(spare.getValue().intValue()).isEqualTo(20);
		assertThat(open.getValue().intValue()).isEqualTo(9);
		open.addExtraCount(DownedPinCount.ALL_PIN_DOWN);
		assertThat(open.getValue().intValue()).isEqualTo(9);
	}
}
