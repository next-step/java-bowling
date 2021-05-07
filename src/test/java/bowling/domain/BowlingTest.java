package bowling.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingTest {


  @Test
  void play() {
    Bowling bowling = new Bowling(new Player("cyr"));
    assertEquals(bowling.play(4).get(0).getScore(),"4");
    assertEquals(bowling.play(6).get(0).getScore(),"4|/");
    assertEquals(bowling.play(10).get(1).getScore(),"X");
  }

  @Test
  @DisplayName("10프레임 스트라이크 시, 두번의 투구를 더준다.")
  void isEnd_10frame_strike() {
    Bowling bowling = new Bowling(new Player("cyr"));
    IntStream.range(0, 9).forEach(index -> bowling.play(10));
    bowling.play(10);
    assertFalse(bowling.isEnd());
    bowling.play(10);
    assertFalse(bowling.isEnd());
    bowling.play(10);
    assertTrue(bowling.isEnd());
  }

  @Test
  @DisplayName("10프레임 미스 시, 종료된다.")
  public void isEnd_10frame_miss() {
    Bowling bowling = new Bowling(new Player("cyr"));
    IntStream.range(0, 9).forEach(index -> bowling.play(10));
    bowling.play(5);
    bowling.play(4);
    assertTrue(bowling.isEnd());
  }

  @Test
  @DisplayName("10프레임 스페어 시, 한번의 투구를 더준다.")
  public void isEnd_10frame_spare() {
    Bowling bowling = new Bowling(new Player("cyr"));
    IntStream.range(0, 9).forEach(index -> bowling.play(10));
    bowling.play(5);
    bowling.play(5);
    assertFalse(bowling.isEnd());
    bowling.play(10);
    assertTrue(bowling.isEnd());
  }

}