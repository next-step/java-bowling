package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameHistoryTest {

    @Test
    void STRIKE_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResult.STRIKE, RuleConfig.NUMBER_OF_PIN);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResult result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResult.STRIKE);
    }

    @Test
    void SPARE_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResult.SPARE, RuleConfig.NUMBER_OF_PIN);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResult result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResult.SPARE);
    }

    @Test
    void MISS_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResult.MISS, 1);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResult result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResult.MISS);
    }

    @Test
    void GUTTER_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResult.GUTTER, 0);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResult result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResult.GUTTER);
    }

    @Test
    void NONE_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResult.NONE, 1);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResult result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResult.NONE);
    }

}