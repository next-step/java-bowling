package bowling.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingTest {

  public static final int count = 4;
  private Bowling bowling;

  @BeforeEach
  void setUp() {
    bowling = new Bowling("최용락");
  }

  @Test
  @DisplayName("볼링을 생성하면, 10개의 frame으로 이루어진 객체가 생성된다.")
  void init() {
    assertEquals(bowling.size(), 10);
  }

  @Test
  @DisplayName("play 하면, 쓰러뜨린 핀의 수가 현재 frame 에 기록된다.")
  void play() {
    bowling.play(4);
    assertEquals(bowling.getCurrentFrame().getFirstHit(), 4);
  }

  @Test
  @DisplayName("play 두번 하면, 쓰러뜨린 핀의 수가 현재 frame 에 기록된다.")
  void play_twice() {
    bowling.play(4);
    bowling.play(3);
    assertEquals(bowling.getFrame(1).getSecondHit(), 3);
  }

  @Test
  @DisplayName("play를 두번하면 다음 frame으로 이동된다.")
  void next_frame() {
    assertEquals(bowling.getCurrentFrameNumber(), 1);
    bowling.play(4);
    bowling.play(4);
    assertEquals(bowling.getCurrentFrameNumber(), 2);
  }

  @Test
  @DisplayName("play를 한번할 때, strike면 다음 frame으로 이동된다.")
  void next_frame_for_strike() {
    assertEquals(bowling.getCurrentFrameNumber(), 1);
    bowling.play(10);
    assertEquals(bowling.getCurrentFrameNumber(), 2);
  }

  @Test
  @DisplayName("10프레임 strike 또는 spare 가 없으면, end")
  void isEnd() {
    IntStream.range(0, 19).forEach(i -> bowling.play(5));
    bowling.play(4);
    assertTrue(bowling.isEnd());
  }

  @Test
  @DisplayName("10프레임 spare 면, bonus 라운드가 있다.")
  void isEnd_spare() {
    IntStream.range(0, 20).forEach(i -> bowling.play(5));
    assertFalse(bowling.isEnd());
  }

  @Test
  @DisplayName("10 프레임 strike면, bonus 라운드가 있다.")
  void isEnd_strike() {
    IntStream.range(0, 18).forEach(i -> bowling.play(5));
    bowling.play(10);
    assertFalse(bowling.isEnd());
  }

  @Test
  @DisplayName("10프레임 spare bonus 프레임 strike 면, end")
  void isEnd_spare_bonus() {
    IntStream.range(0, 20).forEach(i -> bowling.play(5));
    bowling.play(10);
    assertTrue(bowling.isEnd());
  }

  @Test
  @DisplayName("10프레임 strike, bonus 프레임 strike 면, 한번더 bonus 가 있다.")
  void isEnd_strike_strike_bonus2() {
    IntStream.range(0, 18).forEach(i -> bowling.play(5));
    bowling.play(10);
    bowling.play(10);
    assertFalse(bowling.isEnd());

    bowling.play(10);
    assertTrue(bowling.isEnd());
  }



}