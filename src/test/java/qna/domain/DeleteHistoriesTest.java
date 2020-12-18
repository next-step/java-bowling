package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DeleteHistoriesTest {

    @Test
    @DisplayName("리스트에 기록 추가")
    void add() {
        DeleteHistories deleteHistories = new DeleteHistories();

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, UserTest.JAVAJIGI.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));

        Assertions.assertThat(deleteHistories.getFirstUser()).isEqualTo(UserTest.JAVAJIGI);
    }
}
