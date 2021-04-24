package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

  @Test
  @DisplayName("[Score] strike 인 경우 Score 생성 테스트")
  void create_strike_score_test() {
    Pins pins = new Pins(Collections.singletonList(new Pin(10)));

    Score score = Score.of(ScoreSymbol.STRIKE, pins);

    assertThat(score).isEqualTo(new Score(10, 2));
  }

  @Test
  @DisplayName("[Score] spare 인 경우 Score 생성 테스트")
  void create_spare_score_test() {
    Pins pins = new Pins(Collections.singletonList(new Pin(10)));

    Score score = Score.of(ScoreSymbol.SPARE, pins);

    assertThat(score).isEqualTo(new Score(10, 1));
  }

  @Test
  @DisplayName("[Score] miss 인 경우 Score 생성 테스트")
  void create_miss_score_test() {
    List<Pin> pin = Stream.of(5, 4)
        .map(Pin::new)
        .collect(Collectors.toList());
    Pins pins = new Pins(pin);

    Score score = Score.of(ScoreSymbol.MISS, pins);

    assertThat(score).isEqualTo(new Score(pins.totalHitPin(), 0));
  }

  @Test
  @DisplayName("[Score] bowl 메소드 테스트")
  void score_bowl_test() {
    Score score = new Score(10, 2);

    score.bowl(5);

    assertThat(score.getScore()).isEqualTo(15);
    assertThat(score.getLeft()).isEqualTo(1);
  }
}
