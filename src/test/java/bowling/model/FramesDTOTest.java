package bowling.model;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FramesDTOTest {

  @ParameterizedTest
  @MethodSource("provideKnockDownNumWithExpected")
  void roll(List<Integer> knockDownNumbers) {
    Frames frames = new Frames();

    knockDownNumbers.forEach(knockDownNumber -> {
      frames.roll(knockDownNumber);
    });

    System.out.println(new FramesDTO(frames.getFrames()).toString());
  }

  static Stream<Arguments> provideKnockDownNumWithExpected() {
    return Stream.of(
        arguments(
            Arrays.asList(0, 0, 0, 1, 0, 10, 5, 5, 10)
        )
    );
  }
}