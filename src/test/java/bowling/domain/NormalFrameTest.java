package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class NormalFrameTest {

    @DisplayName("NormalFrame 객체 정상 생성")
    @Test
    public void makeNormalFrame_정상() {
        PitchesGroup pitchesGroup = new PitchesGroup();
        assertThatCode(() -> {
            new NormalFrame(pitchesGroup);
        }).doesNotThrowAnyException();
    }

    @DisplayName("1번 투구(Bowling) 하면 인스턴스 변수 PitchesGroup에 기록함")
    @Test
    public void bowling_기록() {
        PitchesGroup pitchesGroup = new PitchesGroup();
        List<Pitch> pitches = pitchesGroup.getPitches();
        NormalFrame normalFrame = new NormalFrame(pitchesGroup);

        normalFrame.bowl(4);

        assertThat(pitches.size()).isEqualTo(1);
        assertThat(pitches.get(0).getHitCounts()).isEqualTo(4);
    }
}
