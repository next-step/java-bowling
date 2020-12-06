package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static qna.domain.Question.PERMISSION_NOT_ALLOWED;

public class QuestionTest {
    public static final Question QUESTION_WRITE_BY_JAVAJIGI = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question QUESTION_WRITE_BY_SUNGMIN = new Question("title2", "contents2").writeBy(UserTest.SUNGMIN);

    @DisplayName("자신의 질문을 삭제하는 경우 삭제 성공")
    @Test
    void delete_success() {
        // when 
        final Throwable thrown = catchThrowable(() ->
                QUESTION_WRITE_BY_JAVAJIGI.delete(UserTest.JAVAJIGI, DeleteHistories.of())
        );

        // then
        assertThat(thrown).isNull();
    }

    @DisplayName("삭제를 요청한 유저가 질문의 작성자가 아닌 경우 예외 반환")
    @Test
    void delete_throw_exception_when_ask_user_not_question_owner() {
        // when 
        final Throwable thrown = catchThrowable(() ->
                QUESTION_WRITE_BY_JAVAJIGI.delete(UserTest.SUNGMIN, DeleteHistories.of())
        );

        // then
        assertThat(thrown).isInstanceOf(CannotDeleteException.class)
                .hasMessage(PERMISSION_NOT_ALLOWED);
    }

}
