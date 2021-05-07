package bowling.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PitchingTest {

  @Test
  void init() {
    Pitching pitching = new Pitching();
    assertAll(
        () -> assertEquals(pitching.leftPins(), 10)
    );
  }

  @Test
  @DisplayName("hit 하면, 차수가 올라가고 남은 핀정보를 가진 Pitching 객체를 리턴한다.")
  void hit() {
    Pitching initPitching = new Pitching();
    Pitching firstPitching = initPitching.play(4);
    assertEquals(firstPitching.leftPins(), 6);
  }

  @Test
  void strike() {
    Pitching pitching = new Pitching();
    Pitching firstPitching = pitching.play(10);
    assertEquals(firstPitching.result(), Result.STRIKE);
  }

  @Test
  void spare() {
    Pitching pitching = new Pitching();
    Pitching first = pitching.play(5);
    Pitching second = first.play(5);
    assertEquals(second.result(), Result.SPARE);
  }

  @Test
  void miss() {
    Pitching pitching = new Pitching();
    assertEquals(pitching.result(), Result.NONE);
    assertEquals(pitching.play(4).result(), Result.MISS);
    assertEquals(pitching.play(4).play(4).result(), Result.MISS);
  }

  @Test
  void isEnd_strike() {
    Pitching pitching = new Pitching();
    Pitching first = pitching.play(10);
    pitching.play(4);
    assertTrue(first.isEnd());
  }

  @Test
  void isEnd_max_count() {
    Pitching pitching = new Pitching();
    Pitching first = pitching.play(5);
    assertFalse(first.isEnd());
    Pitching second = first.play(4);
    assertTrue(second.isEnd());
  }
}