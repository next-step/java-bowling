package bowling;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinCountTest {

  @ParameterizedTest
  @ValueSource(ints = {-1, 15})
  @DisplayName("친 핀의 개수는 0개 이상 10개 이하여야 한다.")
  public void validatePinCount(int number) {
    assertThatThrownBy(() -> {
      new PinCount(number);
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
