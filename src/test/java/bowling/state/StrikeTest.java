package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.Pin;
import bowling.pin.PinTest;
import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {

  private static final int STRIKE = 10;

  @DisplayName("스트라이크 판별이 되엇을때 스트라이크 마크를 출력하는지 확인한다.")
  @Test
  void strikeMark() {
    Strike strike = new Strike(PinTest.from(STRIKE));
    assertThat(strike.scoreMessage()).isEqualTo("X");
  }

  @DisplayName("스트라이크 판별이 되엇을때 스트라이크 쓰러트린 핀 개수를 출력하는지 확인한다.")
  @Test
  void strikeTotalPin() {
    Strike strike = new Strike(PinTest.from(STRIKE));
    assertThat(strike.totalPin()).isEqualTo(Pin.from(STRIKE));
  }

  @DisplayName("스트라이크 판별이 되엇을때 스코어를 확인한다.")
  @Test
  void strikeScore() {
    Strike strike = new Strike(PinTest.from(STRIKE));
    assertThat(strike.score()).isEqualTo(Score.strike());
  }

  @DisplayName("스트라이크 보너스 점수가 공 2개까지 전부 적용되는지 확인한다.")
  @Test
  void scoreCalculate() {
    Strike strike = new Strike(PinTest.from(10));

    Score strikeScore = Score.strike();
    strikeScore = strike.calculateScore(strikeScore);
    strikeScore = strike.calculateScore(strikeScore);

    int total = strike.calculateScore(strikeScore).getScoreDto().getScore();

    assertThat(total).isEqualTo(30);
  }
}