package bowling.repository;

import bowling.domain.Frames;
import bowling.domain.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameRepositoryTest {

    private BowlingGameRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new BowlingGameRepository();
    }

    @Test
    @DisplayName("기본 저장 및 조회")
    void saveAndFind() {
        // given
        Participant lds = new Participant("LDS");
        repository.save(lds, new Frames());

        // when
        Optional<Frames> frames = repository.findByParticipant(lds);

        // then
        assertThat(frames).isPresent();
    }

    @Test
    @DisplayName("프레임 변경 후, 재조회해도 같은 인스턴스가 조회됨")
    void reFind() {
        // given
        Participant lds = new Participant("LDS");
        repository.save(lds, new Frames());

        // when
        Frames frames = repository.findByParticipant(lds).get();
        frames.pitch(10);
        frames.pitch(2);
        Frames frames2 = repository.findByParticipant(lds).get();

        // then
        assertThat(frames).isEqualTo(frames2);
    }

}