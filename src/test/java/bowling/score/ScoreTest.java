package bowling.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

  @DisplayName("스트라이크를 쳤을때 볼카운드2에 점수 10인 스코어 객체가 생성된다.")
  @Test
  void strike() {
    Score strike = Score.strike();
    assertThat(strike.scoreValue().getScore()).isEqualTo(10);
  }

  @DisplayName("스페어를 쳤을때 볼카운드1에 점수 10인 스코어 객체가 생성된다.")
  @Test
  void spare() {
    Score spare = Score.spare();
    assertThat(spare.scoreValue().getScore()).isEqualTo(10);
  }

  @DisplayName("미스를 쳣을때 볼카운0에 점수가 할당된 스코어 객체가 생성된다.")
  @Test
  void miss() {
    Score miss = Score.miss(8);
    assertThat(miss.scoreValue().getScore()).isEqualTo(8);
  }

  @DisplayName("스트라이크와 스페어를 쳤을때, 각각 보너스 적용 볼카운트 만큼 값이 합산되는지 확인한다.")
  @Test
  void sum() {
    assertAll(
        () -> assertThat(Score.spare().sum(5)).isEqualTo(Score.miss(15)),
        () -> assertThat(Score.strike().sum(5).sum(5)).isEqualTo(Score.miss(20))
    );
  }

  @DisplayName("스트라이크와 스페어를 쳤을때, 각각 보너스 적용 볼카운트 를 초과한 만큼 값을 합산하려 하면 에러가 발생한다.")
  @Test
  void validationBallCount() {
    assertAll(
        () -> assertThatThrownBy(
            () -> Score.strike()
                .sum(10)
                .sum(10)
                .sum(10)
        ).isInstanceOf(IllegalArgumentException.class),

        () -> assertThatThrownBy(
            () -> Score.spare()
                .sum(10)
                .sum(10)
        ).isInstanceOf(IllegalArgumentException.class)
    );

  }
}