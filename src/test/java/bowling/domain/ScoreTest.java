package bowling.domain;

import bowling.domain.turn.FallenPins;
import bowling.error.CannotCalculateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

  @Test
  @DisplayName("스트라이크 추가 점수 테스트")
  void strikeScoreTest(){
    Score score = Score.ofStrike();

    Assertions.assertThat(score.bowl(7).bowl(3).getScore()).isEqualTo(20);
  }

  @Test
  @DisplayName("스페어 추가 점수 테스트")
  void spareScoreTest(){
    Score score = Score.ofSpare();

    Assertions.assertThat(score.bowl(8).getScore()).isEqualTo(18);
  }

  @Test
  @DisplayName("일반 샷 기본 점수 테스트")
  void missScoreTest(){
    FallenPins firstShot = new FallenPins(7);
    FallenPins secondShot = new FallenPins(1);
    Score score = Score.ofMiss(firstShot.addShot(secondShot));

    Assertions.assertThat(score.getScore()).isEqualTo(8);
  }


  @Test
  @DisplayName("스트라이크 후 점수가 없을 경우 예외 테스트")
  void strikeWithoutAfterShotTest(){
    Assertions.assertThatThrownBy(() -> Score.ofStrike().getScore()).isInstanceOf(CannotCalculateException.class);
  }

  @Test
  @DisplayName("스페어 후 점수가 없을 경우 예외 테스트")
  void spareWithoutAfterShotTest(){
    Assertions.assertThatThrownBy(() -> Score.ofSpare().getScore()).isInstanceOf(CannotCalculateException.class);
  }
}