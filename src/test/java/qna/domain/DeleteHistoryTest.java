package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {
    @DisplayName("질문글을 삭제한 시간을 확인할 수 있다.")
    @Test
    void now() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        LocalDateTime expect = LocalDateTime.of(2019, 4, 11, 1, 52);
        DeleteHistories deleteHistories = question.delete(UserTest.JAVAJIGI, expect);
        DeleteHistory deleteHistory = deleteHistories.getDeleteHistories().get(0);

        LocalDateTime actual = deleteHistory.getCreateDate();

        assertThat(actual).isEqualTo(expect);
    }
}