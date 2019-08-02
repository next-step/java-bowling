package bowling.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TextArrangeUtilTest {

  @Test
  void 정해진_길이의_중간에_텍스트를_정렬해준다() {
    assertThat(TextArrangeUtil.arrange("LCJ", 8)).isEqualTo("  LCJ   ");
    assertThat(TextArrangeUtil.arrange("5|/", 8)).isEqualTo("  5|/   ");
  }
}