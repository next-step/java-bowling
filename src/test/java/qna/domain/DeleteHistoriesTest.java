package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("DeleteHistories 클래스 테스트")
class DeleteHistoriesTest {

    @DisplayName("DeleteHistory 객체를 추가할 수 있다.")
    @Test
    void add() {
        Long questionId = 1L;
        User writer = new User();
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now());

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(deleteHistory);

        assertAll(
                () -> assertThat(deleteHistories.getDeleteHistories()).hasSize(1),
                () -> assertThat(deleteHistories.getDeleteHistories().get(0))
                        .isEqualTo(new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now()))
        );
    }

}
