package qna.domain.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.user.UserTest;
import qna.exception.IsNotDeletedException;
import qna.domain.question.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("답변이 없는 경우 삭제 가능")
    void noAnswers() {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제되지 않은 질문의 삭제 기록 조회 시 예외 처리")
    void deleteHistoryException() {
        assertThatThrownBy(Q2::deleteHistories).isExactlyInstanceOf(IsNotDeletedException.class);
    }
}
