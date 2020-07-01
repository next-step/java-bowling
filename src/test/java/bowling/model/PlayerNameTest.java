package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerNameTest {

  @Test
  void init_NotEnglish() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      new PlayerName("한글");
    });
  }

  @Test
  void init_Over3Letters() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      new PlayerName("");
    });

    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      new PlayerName("asdf");
    });
  }

  @Test
  void getValue() {
    PlayerName playerName = new PlayerName("abc");

    assertThat(playerName.getValue()).isEqualTo("abc");
  }
}