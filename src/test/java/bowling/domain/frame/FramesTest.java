package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("프레임들")
class FramesTest {

    @DisplayName("[성공] 투구")
    @ParameterizedTest
    @CsvSource({
        "2,1",
        "3,2",
        "4,2",
        "5,3",
        "9,5",
        "10,5",
    })
    public void pitch(final int pitchCount, final int expected) {
        // given
        final Frames frames = Frames.of();

        // when
        for (int i = 0; i < pitchCount; i++) {
            frames.pitch(PitchResult.zero());
        }

        // then
        assertThat(frames.size()).isEqualTo(expected);
    }
}
