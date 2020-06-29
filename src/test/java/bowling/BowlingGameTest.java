package bowling;

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
    BowlingGame game = new BowlingGame();
    game.roll(10);
    while (!game.requiredBonusFrame()) {
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
    BowlingGame game = new BowlingGame();

    for (int i = 0; !game.requiredBonusFrame(); i++) {
      game.roll(kockNumbers.get(i));
    }

    game.initBonusCnt();

    bonusNumbers.stream()
        .filter(n -> game.hasBonus())
        .forEach(game::bonusRoll);

    System.out.println(game.toString());
  }

  static Stream<Arguments> provideKnockNumber_SpareAtLastFrame() {
    return Stream.of(
        arguments(
            Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 1),
            Arrays.asList(10,10)
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideKnockNumber_StrikeAtLastFrame")
  void roll_StikeAtLastFrame(List<Integer> kockNumbers, List<Integer> bonusNumbers) {
    BowlingGame game = new BowlingGame();

    for (int i = 0; !game.requiredBonusFrame(); i++) {
      game.roll(kockNumbers.get(i));
    }

    game.initBonusCnt();

    bonusNumbers.stream()
        .filter(n -> game.hasBonus())
        .forEach(game::bonusRoll);

    System.out.println(game.toString());
  }

  static Stream<Arguments> provideKnockNumber_StrikeAtLastFrame() {
    return Stream.of(
        arguments(
            Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
            Arrays.asList(10,10,10)
        )
    );
  }
}