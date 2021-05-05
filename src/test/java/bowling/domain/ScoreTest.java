package bowling.domain;

import bowling.error.CannotCalculateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
  @Test
  @DisplayName("스트라이크 후 점수가 없을 경우 예외 테스트")
  void strikeWithoutAfterShotTest(){
    Assertions.assertThatThrownBy(() -> Score.ofStrike().getScore()).isInstanceOf(CannotCalculateException.class);
  }


}