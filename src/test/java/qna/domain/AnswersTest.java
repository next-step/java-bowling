package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

public class AnswersTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answers 삭제 처리 후 DeleteHistory List 반환")
    void answersDeleteHistories() throws CannotDeleteException {
        // given
        Answers answers = new Answers();
        answers.add(A1);

        // when
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        //then
        Assertions.assertThat(deleteHistories.get(0))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("Answer 삭제 실패 : loginUser 외 댓글 작성자 여부 확인")
    void delete_fail_different_comment_user() {
        // given
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        // when, then
        Assertions.assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}