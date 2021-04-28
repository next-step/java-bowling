package bowling.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PitchingTest {

  @Test
  void getResult_MISS() {
    Pitching pitching = Pitching.firstPitching(1);
    assertEquals(pitching.getResult(), Result.MISS);

    Pitching secondPitching = pitching.secondPitching(1);
    assertEquals(secondPitching.getResult(), Result.MISS);
  }

  @Test
  void getResult_STRIKE() {
    Pitching pitching = Pitching.firstPitching(10);
    assertEquals(pitching.getResult(), Result.STRIKE);
  }

  @Test
  void getResult_SPARE() {
    Pitching pitching = Pitching.firstPitching(1);
    Pitching secondPitching = pitching.secondPitching(9);
    assertEquals(secondPitching.getResult(), Result.SPARE);
  }

  @Test
  void getResult_GUTTER() {
    Pitching pitching = Pitching.firstPitching(0);
    assertEquals(pitching.getResult(), Result.GUTTER);

    Pitching secondPitching = pitching.secondPitching(0);
    assertEquals(secondPitching.getResult(), Result.GUTTER);
  }
}