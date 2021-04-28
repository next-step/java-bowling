package bowling.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FrameTest {

  private static final String SCORE_FORMAT = "  %s|%s ";
  private static final String STRIKE_FORMAT = "  %s   ";
  private static final String GUTTER = "-";
  private Frame frame;

  @BeforeEach
  void setUp() {
    frame = new Frame();
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 5, 10})
  @DisplayName("1구 플레이 시, 쓰러뜨린 핀의 수를 구한다.")
  void playFirst(int hitCount) {
    frame.play(hitCount);
    assertEquals(frame.getFirstHit(), hitCount);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 5, 9})
  @DisplayName("2구 플레이 시, 쓰러뜨린 핀의 수를 구한다.")
  void playSecond(int hitCount) {
    frame.play(1);
    frame.play(hitCount);
    assertEquals(frame.getSecondHit(), hitCount);
  }

  @Test
  @DisplayName("1구에 핀을 모두 쓸어뜨렸다면, Strike 이다.")
  void isStrike() {
    frame.play(10);
    assertTrue(frame.isStrike());
  }

  @Test
  @DisplayName("2구에 핀을 모두 쓰러뜨리면, spare 이다.")
  void isSpare() {
    frame.play(5);
    frame.play(5);
    assertTrue(frame.getResult().isSpare());
  }

  @Test
  @DisplayName("strike한 frame은 종료된다.")
  void isEnd_true_strike() {
    frame.play(10);
    assertTrue(frame.isEnd());
  }

  @Test
  @DisplayName("두번 플레이한 frame은 종료된다.")
  void isEnd_true() {
    frame.play(4);
    frame.play(4);
    assertTrue(frame.isEnd());
  }

  @Test
  @DisplayName("strike가 아닌 한번 플레이한 frame은 종료되지 않는다.")
  void isEnd_false() {
    frame.play(4);
    assertFalse(frame.isEnd());
  }

  @Test
  @DisplayName("strike 면, bonus가 있다.")
  void hasBonus_strike() {
    frame.play(10);
    assertTrue(frame.hasBonus());
  }

  @Test
  @DisplayName("spare 면, bonus가 있다.")
  void hasBonus_spare() {
    frame.play(4);
    frame.play(6);
    assertTrue(frame.hasBonus());
  }

  @Test
  @DisplayName("strike, spare 가 아니면 bonus가 없다.")
  void hasBonus_false() {
    frame.play(4);
    frame.play(4);
    assertFalse(frame.hasBonus());
  }

}