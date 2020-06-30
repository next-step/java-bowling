package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BowlingGameTest {

  @Test
  void roll() {
    BowlingGame game = BowlingGame.createWith("abc");
    game.roll(10);
    while (game.requiredNormalFrame()) {
      game.roll(1);
    }

    while (game.hasBonus()) {
      game.bonusRoll(2);
    }

    System.out.println(game.toString());
  }

  @ParameterizedTest
  @MethodSource("provideKnockNumber_SpareAtLastFrame")
  void roll_SpareAtLastFrame(List<Integer> kockNumbers, List<Integer> bonusNumbers) {
    BowlingGame game = BowlingGame.createWith("abc");

    for (int i = 0; game.requiredNormalFrame(); i++) {
      game.roll(kockNumbers.get(i));
    }

    game.initBonusCount();

    bonusNumbers.stream()
        .filter(n -> game.hasBonus())
        .forEach(game::bonusRoll);

    System.out.println(game.toString());
  }

  static Stream<Arguments> provideKnockNumber_SpareAtLastFrame() {
    return Stream.of(
        arguments(
            Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 1),
            Arrays.asList(10, 10)
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideKnockNumber_StrikeAtLastFrame")
  void roll_StikeAtLastFrame(List<Integer> kockNumbers, List<Integer> bonusNumbers) {
    BowlingGame game = BowlingGame.createWith("abc");

    for (int i = 0; game.requiredNormalFrame(); i++) {
      game.roll(kockNumbers.get(i));
    }

    game.initBonusCount();

    bonusNumbers.stream()
        .filter(n -> game.hasBonus())
        .forEach(game::bonusRoll);

    System.out.println(game.toString());
  }

  static Stream<Arguments> provideKnockNumber_StrikeAtLastFrame() {
    return Stream.of(
        arguments(
            Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
            Arrays.asList(10, 10, 10)
        )
    );
  }

  @Test
  void getPlayerName() {
    assertThat(BowlingGame.createWith("abc").getPlayerName()).isEqualTo("abc");
  }
}