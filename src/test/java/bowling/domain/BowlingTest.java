package bowling.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingTest {

  public static final int count = 4;
  private Bowling bowling = new Bowling("최용락");

  @Test
  @DisplayName("볼링을 생성하면, 10개의 frame으로 이루어진 객체가 생성된다.")
  void init() {
    assertEquals(bowling.size(), 10);
  }

  @Test
  @DisplayName("play 하면, 쓰러뜨린 핀의 수가 현재 frame 에 기록된다.")
  void play() {
    bowling.play(4);
    assertEquals(bowling.getFrame(1).getFirstHit(), 4);
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
    assertEquals(bowling.getCurrent(), 1);
    bowling.play(4);
    bowling.play(4);
    assertEquals(bowling.getCurrent(), 2);
  }

  @Test
  @DisplayName("play를 한번할 때, strike면 다음 frame으로 이동된다.")
  void next_frame_for_strike() {
    assertEquals(bowling.getCurrent(), 1);
    bowling.play(10);
    assertEquals(bowling.getCurrent(), 2);
  }
}