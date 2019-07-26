package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingGameResultTest {

  BowlingGameResult gameResult;

  @BeforeEach
  void 생성() {
    gameResult = new BowlingGameResult();
  }

  @Test
  void 결과입력_및_조회() {
    gameResult.record(1, "X");
    gameResult.record(2, "5|/");
    assertThat(gameResult.result(1)).isEqualTo("X");
    assertThat(gameResult.result(2)).isEqualTo("5|/");
  }

  @Test
  void 해당프레임_결과가_있는지_확인한다() {
    gameResult.record(1, "X");
    assertThat(gameResult.hasResult(1)).isTrue();
    assertThat(gameResult.hasResult(2)).isFalse();

  }
}
