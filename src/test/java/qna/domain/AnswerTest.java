package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qna.domain.Answer.EXIST_ANOTHER_USER_ANSWER;

public class AnswerTest {
    public static final Answer QUESTION_AND_ANSWER_WRITE_BY_JAVAJIGI = new Answer(UserTest.JAVAJIGI, QuestionTest.QUESTION_WRITE_BY_JAVAJIGI, "Answers Contents1");
    public static final Answer QUESTION_WRITE_BY_JAVAJIGI_AND_ANSWER_WRITE_BY_SUNGMIN = new Answer(UserTest.SUNGMIN, QuestionTest.QUESTION_WRITE_BY_JAVAJIGI, "Answers Contents2");

    @DisplayName("답변 작성자가 삭제를 하는 경우 성공")
    @Test
    void success_when_owner_delete_answer() throws CannotDeleteException {
        // given
        final Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.QUESTION_WRITE_BY_JAVAJIGI, "Answers Contents1");
        final DeleteHistories actualDeleteHistories = DeleteHistories.of();

        final DeleteHistories expectedDeleteHistories = DeleteHistories.of();
        expectedDeleteHistories.save(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));

        // when
        answer.delete(UserTest.JAVAJIGI, actualDeleteHistories);

        // then
        assertThat(answer.isDeleted()).isTrue();
        assertThat(actualDeleteHistories).isEqualTo(expectedDeleteHistories);
    }

    @DisplayName("작성자 이외의 유저가 답변을 삭제하는 경우 예외 반환")
    @Test
    void throw_exception_when_not_owner_delete_answer() {
        final Answer answer = new Answer(UserTest.SUNGMIN, QuestionTest.QUESTION_WRITE_BY_JAVAJIGI, "Answers Contents2");
        
        // when 
        final Throwable thrown = catchThrowable(() ->
                answer.delete(UserTest.JAVAJIGI, DeleteHistories.of())
        );

        // then
        assertThat(thrown).isInstanceOf(CannotDeleteException.class)
                .hasMessage(EXIST_ANOTHER_USER_ANSWER);

        assertThat(answer.isDeleted()).isFalse();
    }
}
