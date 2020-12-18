package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {

    @DisplayName("DeleteHistories 합치기 테스트")
    @Test
    void concat() {
        DeleteHistory given1 = new DeleteHistory(ContentType.ANSWER, 1L, JAVAJIGI, LocalDateTime.now());
        DeleteHistory given2 = new DeleteHistory(ContentType.ANSWER, 2L, SANJIGI, LocalDateTime.now());

        DeleteHistories deleteHistory1 = new DeleteHistories(given1);
        DeleteHistories deleteHistory2 = new DeleteHistories(given2);
        List<DeleteHistory> deleteHistories = Arrays.asList(given1, given2);

        assertThat(DeleteHistories.concat(deleteHistory1, deleteHistory2)).isEqualTo(new DeleteHistories(deleteHistories));
    }
}
