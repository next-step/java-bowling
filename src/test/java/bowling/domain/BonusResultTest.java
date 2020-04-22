package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import bowling.exception.CannotBowlException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusResultTest {

  @ParameterizedTest
  @ValueSource(ints = {-1, 3})
  public void testInvalidBonusBowl(int bonusBallCount) {
    assertThatThrownBy(() -> new BonusResult(bonusBallCount))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2})
  public void testExceedMaxTrial(int bonusBallCount) throws CannotBowlException {
    BonusResult bonusResult = new BonusResult(bonusBallCount);

    for (int i = 0; i < bonusBallCount; i++) {
      bonusResult.roll(3);
    }

    assertThat(bonusResult.isFinished()).isTrue();
    assertThatThrownBy(() -> bonusResult.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @Test
  public void testBowlOverTen() throws CannotBowlException {
    BonusResult bonusResult = new BonusResult(2);
    bonusResult.roll(5);
    assertThatThrownBy(() -> bonusResult.roll(6))
      .isInstanceOf(CannotBowlException.class);
  }
}
