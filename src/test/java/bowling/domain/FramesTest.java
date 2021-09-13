package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("currentFrame 테스트")
    public void currentFrame() {
        // given
        Frames frames = new Frames();
        int expectedCurrentFrame = 3;

        // when
        boolean firstPitchResult = frames.addPitch(new Pitch(10));
        boolean secondPitchResult = frames.addPitch(new Pitch(0));
        boolean thirdPitchResult = frames.addPitch(new Pitch(10));
        List<List<Integer>> allFramePitchValues = frames.allFramePitchValues();

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(thirdPitchResult).isEqualTo(true);
        assertThat(allFramePitchValues)
                .contains(
                        Collections.singletonList(10),
                        Arrays.asList(0, 10),
                        Collections.emptyList());
        assertThat(frames.currentFrameNumber()).isEqualTo(expectedCurrentFrame);
    }

    @Test
    @DisplayName("addPitch,allFramePitchValues,isEnd,scores 테스트 - 전부 거터 (점수 없음)")
    public void allGutter() {
        // given
        Frames frames = new Frames();
        List<Integer> expectedPitchValues = Arrays.asList(0, 0);

        // when
        List<Boolean> results = allGutter(frames);
        List<List<Integer>> allFramePitchValues = frames.allFramePitchValues();

        // then
        assertThat(results).allMatch(result -> result);
        assertThat(allFramePitchValues)
                .allMatch(pitchValues -> pitchValues.equals(expectedPitchValues));
        assertThat(frames.isEnd()).isEqualTo(true);
        assertThat(frames.scores()).allMatch(score -> score == 0);
    }

    @Test
    @DisplayName("addPitch,allFramePitchValues,isEnd,scores 테스트 - 퍼펙트 게임")
    public void perfectGame() {
        // given
        Frames frames = new Frames();
        List<Integer> normalExpected = Collections.singletonList(10);
        List<Integer> finalExpected = Arrays.asList(10, 10, 10);

        // when
        List<Boolean> results = perfectGame(frames);
        List<List<Integer>> allFramePitchValues = frames.allFramePitchValues();
        List<List<Integer>> normalFramePitchValues = allFramePitchValues.stream()
                .limit(allFramePitchValues.size() - 1)
                .collect(Collectors.toList());
        List<Integer> finalFramePitchValues = allFramePitchValues.get(allFramePitchValues.size() - 1);

        // then
        assertThat(results).allMatch(result -> result);
        assertThat(normalFramePitchValues)
                .allMatch(pitchValues -> pitchValues.equals(normalExpected));
        assertThat(finalFramePitchValues).isEqualTo(finalExpected);
        assertThat(frames.isEnd()).isEqualTo(true);
        assertThat(frames.scores()).containsExactly(30, 60, 90, 120, 150, 180, 210, 240, 270, 300);
    }

    private List<Boolean> allGutter(final Frames frames) {
        return IntStream.range(0, 20)
                .mapToObj(i -> new Pitch(0))
                .map(frames::addPitch)
                .collect(Collectors.toList());
    }

    private List<Boolean> perfectGame(final Frames frames) {
        return IntStream.range(0, 12)
                .mapToObj(i -> new Pitch(10))
                .map(frames::addPitch)
                .collect(Collectors.toList());
    }

}