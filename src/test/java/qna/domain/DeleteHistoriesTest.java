package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    @Test
    @DisplayName("DeleteHistories 병합하기")
    void combine() {
        // given
        DeleteHistory deleteHistory1 = new DeleteHistory(ContentType.QUESTION, 11L, UserTest.JAVAJIGI, LocalDateTime.now());
        DeleteHistory deleteHistory2 = new DeleteHistory(ContentType.ANSWER, 12L, UserTest.JAVAJIGI, LocalDateTime.now());
        DeleteHistory deleteHistory3 = new DeleteHistory(ContentType.ANSWER, 13L, UserTest.JAVAJIGI, LocalDateTime.now());

        DeleteHistories deleteHistoriesOfQuestion = DeleteHistories.of(deleteHistory1);
        DeleteHistories deleteHistoriesOfAnswers = DeleteHistories.of(deleteHistory2, deleteHistory3);

        DeleteHistories expected = DeleteHistories.of(deleteHistory1, deleteHistory2, deleteHistory3);

        // when
        DeleteHistories result = deleteHistoriesOfQuestion.combine(deleteHistoriesOfAnswers);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
