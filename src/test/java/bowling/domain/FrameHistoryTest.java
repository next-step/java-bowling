package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameHistoryTest {

    @Test
    void STRIKE_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResultEnum.STRIKE, RuleConfig.NUMBER_OF_PIN);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResultEnum result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResultEnum.STRIKE);
    }

    @Test
    void SPARE_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResultEnum.SPARE, RuleConfig.NUMBER_OF_PIN);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResultEnum result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResultEnum.SPARE);
    }

    @Test
    void MISS_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResultEnum.MISS, 1);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResultEnum result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResultEnum.MISS);
    }

    @Test
    void GUTTER_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResultEnum.GUTTER, 0);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResultEnum result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResultEnum.GUTTER);
    }

    @Test
    void NONE_record_test() {
        FrameHistory frameHistory = new FrameHistory();
        frameHistory.record(PitchResultEnum.NONE, 1);

        List<PitchHistory> pitchHistories = frameHistory.getPitchHistories();
        PitchResultEnum result = pitchHistories.get(0).getResult();

        assertThat(pitchHistories).hasSize(1);
        assertThat(result).isEqualTo(PitchResultEnum.NONE);
    }

}