package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.TestFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeleteHistoryTest {

    @Test
    @DisplayName("Question 삭제 기록 정적팩토리 메서드 테스트")
    void questionDeleteHistory() {
        // given
        final long questionId = 10L;

        // when
        DeleteHistory questionDeleteHistory = DeleteHistory.createQuestionHistory(questionId, TestFixture.JAVAJIGI);

        // then
        assertAll(
                () -> assertThat(questionDeleteHistory.getContentType()).isEqualTo(ContentType.QUESTION),
                () -> assertThat(questionDeleteHistory.getContentId()).isEqualTo(questionId),
                () -> assertThat(questionDeleteHistory.getDeletedBy()).isEqualTo(TestFixture.JAVAJIGI)
        );
    }
}
