package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static qna.domain.Answers.ANSWER_LIST_MUST_NOT_NULL;
import static qna.domain.Answers.EXIST_ANOTHER_USER_ANSWER;

public class AnswersTest {

    @DisplayName("생성 성공")
    @Test
    void create() {
        // given
        final List<Answer> answerList = Arrays.asList(AnswerTest.QUESTION_AND_ANSWER_WRITE_BY_JAVAJIGI, AnswerTest.QUESTION_WRITE_BY_JAVAJIGI_AND_ANSWER_WRITE_BY_SUNGMIN);
        // when
        final Answers answers = Answers.valueOf(answerList);
        // then
        assertThat(answers).isNotNull();
    }

    @DisplayName("null을 전달한 경우 생성 실패")
    @Test
    void create_throw_exception() {
        // when
        final Throwable thrown = catchThrowable(() -> {
            Answers.valueOf(null);
        });

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ANSWER_LIST_MUST_NOT_NULL);
    }

    @DisplayName("작성자 이외의 유저가 답변을 작성한 경우 예외 반환")
    @Test
    void throw_exception_whenanother_user_answer_the_question() {
        // given
        final List<Answer> anotherUserAnswerList = Collections.singletonList(AnswerTest.QUESTION_WRITE_BY_JAVAJIGI_AND_ANSWER_WRITE_BY_SUNGMIN);
        final Answers anotherUserAnswers = Answers.valueOf(anotherUserAnswerList);

        // when 
        final Throwable thrown = catchThrowable(() ->
                anotherUserAnswers.delete(UserTest.JAVAJIGI, DeleteHistories.of())
        );

        // then
        assertThat(thrown).isInstanceOf(CannotDeleteException.class)
                .hasMessage(EXIST_ANOTHER_USER_ANSWER);
    }

    @DisplayName("작성자만 답변을 작성한 경우")
    @Test
    void another_user_not_answer_the_question() {
        // given
        final List<Answer> anotherUserAnswerList = Collections.singletonList(AnswerTest.QUESTION_AND_ANSWER_WRITE_BY_JAVAJIGI);
        final Answers anotherUserAnswers = Answers.valueOf(anotherUserAnswerList);

        // when 
        final Throwable thrown = catchThrowable(() ->
                anotherUserAnswers.delete(UserTest.JAVAJIGI, DeleteHistories.of())
        );

        // then
        assertThat(thrown).isNull();
    }

}
