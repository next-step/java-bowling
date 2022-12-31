package bowling.domain.dto;

import bowling.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultsDtoTest {

    public static final String PLAYER_NAME_HSH = "HSH";

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 10})
    void frame_최대개수만큼_생성(int frameResultsLength) {
        assertThat(frameResultsDtoProvider(PLAYER_NAME_HSH, frameResultsLength).results())
                .hasSize(11);
    }

    @Test
    void player_이름이_결과리스트의_첫번째원소에_들어가야_한다() {
        assertThat(frameResultsDtoProvider(PLAYER_NAME_HSH, 1).results().get(0))
                .isEqualTo(PLAYER_NAME_HSH);
    }

    private FrameResultsDto frameResultsDtoProvider(String playerName, int frameResultsLength) {
        if (frameResultsLength > Frames.MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 최대 10개까지만 생성 가능합니다.");
        }

        List<Frame> frameResults = new ArrayList<>();
        for (int frameNumber = 1; frameNumber <= frameResultsLength; frameNumber++) {
            frameResults.add(frameImplProvider(frameNumber));
        }

        return new FrameResultsDto(new Player(playerName), frameResults);
    }

    private Frame frameImplProvider(int frameNumber) {
        if (frameNumber == 10) {
            return new FinalFrame();
        }
        return new NormalFrame(frameNumber);
    }
}