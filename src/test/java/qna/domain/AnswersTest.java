package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static qna.domain.Answers.ANSWER_LIST_MUST_NOT_NULL;

public class AnswersTest {

    @DisplayName("생성 성공")
    @Test
    void create() {
        // given
        final List<Answer> answerList = Arrays.asList(AnswerTest.QUESTION_AND_ANSWER_WRITE_BY_JAVAJIGI,
                AnswerTest.QUESTION_WRITE_BY_JAVAJIGI_AND_ANSWER_WRITE_BY_SUNGMIN);
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

    @DisplayName("답변이 없는 경우")
    @Test
    void not_exist_answer() {
        // given
        final Answers anotherUserAnswers = Answers.valueOf(Collections.emptyList());

        // when 
        final Throwable thrown = catchThrowable(() ->
                anotherUserAnswers.delete(UserTest.JAVAJIGI, DeleteHistories.of())
        );

        // then
        assertThat(thrown).isNull();
    }

}
