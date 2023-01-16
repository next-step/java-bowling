package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultLinesTest {

    public static final String PLAYER_NAME_HSH = "HSH";

    @Test
    void firstline_생성() {
        assertThat(ResultLines.FRAME_NUMBERS.get(0)).isEqualTo(ResultLines.NAME_MESSAGE);
        assertThat(ResultLines.FRAME_NUMBERS).hasSize(11);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 10})
    void frameResults_최대개수만큼_생성(int frameResultsLength) {
        assertThat(resultsLinesProvider(PLAYER_NAME_HSH, frameResultsLength).frameResults())
                .hasSize(11);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 10})
    void frameScores_최대개수만큼_생성(int frameResultsLength) {
        assertThat(resultsLinesProvider(PLAYER_NAME_HSH, frameResultsLength).frameScores())
                .hasSize(11);
    }

    @Test
    void player_이름이_결과리스트의_첫번째원소에_들어가야_한다() {
        assertThat(resultsLinesProvider(PLAYER_NAME_HSH, 1).frameResults().get(0))
                .isEqualTo(PLAYER_NAME_HSH);
    }

    private ResultLines resultsLinesProvider(String playerName, int frameResultsLength) {
        if (frameResultsLength > Frames.MAX_FRAMENUMBER) {
            throw new IllegalArgumentException("프레임은 최대 10개까지만 생성 가능합니다.");
        }

        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 1; frameNumber <= frameResultsLength; frameNumber++) {
            frames.add(FrameFactory.frameImplProvider(frameNumber));
        }

        return new ResultLines(new Player(playerName), frames);
    }

}