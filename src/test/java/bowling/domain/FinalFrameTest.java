package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FinalFrameTest {

  @Test
  void strike() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isFalse();
    assertThat(finalFrame.symbol()).isEqualTo("X");
  }

  @Test
  void triple() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);
    finalFrame.roll(10);
    finalFrame.roll(10);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("X|X|X");
  }


  @Test
  void double_open() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);
    finalFrame.roll(10);
    finalFrame.roll(9);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("X|X|9");
  }

  @Test
  void double_gutter() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);
    finalFrame.roll(10);
    finalFrame.roll(0);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("X|X|-");
  }

  @Test
  void strike_spare() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);
    finalFrame.roll(8);
    finalFrame.roll(2);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("X|8|/");
  }

  @Test
  void strike_open() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);
    finalFrame.roll(8);
    finalFrame.roll(1);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("X|8|1");
  }

  @Test
  void strike_gutter() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);
    finalFrame.roll(8);
    finalFrame.roll(0);

    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("X|8|-");
  }

  @Test
  void spare_strike() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(8);
    finalFrame.roll(2);
    finalFrame.roll(10);

    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("8|/|X");
  }

  @Test
  void spare_open() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(8);
    finalFrame.roll(2);
    finalFrame.roll(8);

    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("8|/|8");
  }

  @Test
  void spare_gutter() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(8);
    finalFrame.roll(2);
    finalFrame.roll(0);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("8|/|-");
  }

  @Test
  void open() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(8);
    finalFrame.roll(1);

    System.out.println(finalFrame);
    assertThat(finalFrame.isDone()).isTrue();
    assertThat(finalFrame.symbol()).isEqualTo("8|1");
  }

  @Test
  void strike_score() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);

    System.out.println(finalFrame.score(null));
  }

  @Test
  void triple_score() {
    FinalFrame finalFrame = new FinalFrame();
    finalFrame.roll(10);
    finalFrame.roll(10);
    finalFrame.roll(10);

    System.out.println(finalFrame.score(null));
  }
}
