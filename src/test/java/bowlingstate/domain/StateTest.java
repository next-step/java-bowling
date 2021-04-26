package bowlingstate.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowlingstate.domain.state.FirstBowl;
import bowlingstate.domain.state.Miss;
import bowlingstate.domain.state.Ready;
import bowlingstate.domain.state.Spare;
import bowlingstate.domain.state.State;
import bowlingstate.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateTest {

  @Test
  @DisplayName("[State] Ready 상태, 끝나지 않았는지 판정 메소드 테스트")
  void ready_state_finished_test() {
    State state = new Ready();

    assertThat(state.isFinished()).isFalse();
  }

  @Test
  @DisplayName("[State] Ready 일 때, hitPin이 10개인 경우 Strike 반환 테스트")
  void ready_state_bowl_strike_test() {
    State state = new Ready().bowl(10);

    assertThat(state.getClass()).isEqualTo(Strike.class);
  }

  @Test
  @DisplayName("[State] Ready 일 때, hitPin이 7개인 경우 FirstBowl 반환 테스트")
  void ready_state_bowl_miss_test() {
    State state = new Ready().bowl(7);

    assertThat(state.getClass()).isEqualTo(FirstBowl.class);
  }

  @Test
  @DisplayName("[State] FirstBowl 일 때, 스페어 처리시 Spare 반환 테스트")
  void firstBowl_spare_test() {
    State state = new FirstBowl(5).bowl(5);

    assertThat(state.getClass()).isEqualTo(Spare.class);
  }

  @Test
  @DisplayName("[State] FirstBowl 일 때, 스페어 처리실패시 Miss 반환 테스트")
  void firstBowl_miss_test() {
    State state = new FirstBowl(5).bowl(4);

    assertThat(state.getClass()).isEqualTo(Miss.class);
  }

  @Test
  @DisplayName("[State] Ready 일 때, FirstBowl 반환시 frameState 리스트 확인 테스트")
  void firstBowl_state_list_test() {
    State state = new Ready().bowl(7);

    int size = state.state().size();

    assertThat(size).isEqualTo(1);
  }

  @Test
  @DisplayName("[State] FirstBowl 일 때, Miss 반환시 frameState 리스트 확인 테스트")
  void miss_state_list_test() {
    State state = new FirstBowl(5).bowl(4);

    int size = state.state().size();

    assertThat(size).isEqualTo(2);
  }
}
