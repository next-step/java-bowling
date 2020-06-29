package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FrameDTOTest {

  @ParameterizedTest
  @MethodSource("provideFrame")
  void createBy(Frame frame) {
    FrameDTO frameDTO = FrameDTO.createBy(frame);

    System.out.println(frameDTO);

  }

  static Stream<Arguments> provideFrame() {
    return Stream.of(
        arguments(
            FramesTest.normalFrame0_0
        ),
        arguments(
            FramesTest.normalFrame0_null
        ),
        arguments(
            FramesTest.normalFrame0_1
        ),
        arguments(
            FramesTest.normalFrame0_10
        ),
        arguments(
            FramesTest.normalFrame5_5
        ),
        arguments(
            FramesTest.normalFrameStrike
        ),
        arguments(
            FramesTest.normalFrame5_0
        ),
        arguments(
            FramesTest.normalFrame5_1
        )
    );
  }
}