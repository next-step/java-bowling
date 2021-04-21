package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoundTest {

  @Test
  @DisplayName("[Round] 첫번째 라운드 생성 테스트")
  void create_first_round_test() {
    Round round = new Round(1);
    assertThat(round).isEqualTo(Round.firstRound());
  }

  @Test
  @DisplayName("[Round] 마지막 라운드 생성 테스트")
  void create_final_round_test() {
    Round round = new Round(10);
    assertThat(round).isEqualTo(Round.finalRound());
  }

  @Test
  @DisplayName("[Round] 마지막 판정 테스트")
  void is_final_round_test() {
    Round round = new Round(10);
    assertThat(round.isFinalRound()).isTrue();
  }




}
