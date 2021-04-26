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
    assertTrue(frame.isSpare());
  }

  @Test
  @DisplayName("이전 strike 에 더할 보너스 점수를 구한다.")
  void getBonusBeforeFrame_strike() {
    frame.play(4);
    frame.play(6);
    int bonus = frame.getBonusBeforeFrame(Result.STRIKE);
    assertEquals(bonus, 10);
  }

  @Test
  @DisplayName("이전 strike 에 더할 보너스 점수를 구한다.")
  void getBonusBeforeFrame_spare() {
    frame.play(4);
    frame.play(6);
    int bonus = frame.getBonusBeforeFrame(Result.SPARE);
    assertEquals(bonus, 4);
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

}