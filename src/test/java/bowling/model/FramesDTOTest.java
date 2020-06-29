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
  @MethodSource("provideKonckDownNumWithExpected")
  void roll(List<Integer> knockDownNums) {
    Frames frames = new Frames();

    knockDownNums.forEach(knockDownNum -> {
      frames.roll(knockDownNum);
    });

    System.out.println(new FramesDTO(frames.getFrames()).toString());
  }

  static Stream<Arguments> provideKonckDownNumWithExpected() {
    return Stream.of(
        arguments(
            Arrays.asList(0, 0, 0, 1, 0, 10, 5, 5, 10)
        )
    );
  }
}