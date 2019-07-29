package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScoreTest {

  @Test
  void 생성() {
    Score score = new Score(10,2);
  }

  @Test
  void 동치테스트() {
    assertThat(new Score(5,0)).isEqualTo(new Score(5,0));
  }

  @Test
  void 추가점수처리() {
    assertThat(new Score(10,1).addScore(8,1)).isEqualTo(new Score(18,0));
  }

  @Test
  void 스트라이크_Score반환() {
    assertThat(Score.strike()).isEqualTo(new Score(10,2));
  }

  @Test
  void 스패어_Score반환() {
    assertThat(Score.spare()).isEqualTo(new Score(10,1));
  }

  @Test
  void 더이상_더해질_점수가_없는지_확인() {
    assertThat(new Score(10,1).hasNoAdditionalScore()).isFalse();
    assertThat(new Score(15,0).hasNoAdditionalScore()).isTrue();
  }
}
