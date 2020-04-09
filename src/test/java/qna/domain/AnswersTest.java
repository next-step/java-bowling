package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @Test
    @DisplayName("삭제 가능 테스트")
    void isDeletableTest() {
        Answers answers = new Answers(Collections.singletonList(AnswerTest.A1));
        assertThat(answers.isNotDeletable(AnswerTest.A1.getWriter()))
                .isFalse();
    }

    @Test
    @DisplayName("삭제 가능 여부 확인 테스트")
    void isNotDeletableTest() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThat(answers.isNotDeletable(AnswerTest.A2.getWriter()))
                .isTrue();
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() throws CannotDeleteException {
        Answers answers = new Answers(Collections.singletonList(AnswerTest.A1));
        assertThat(answers.delete(AnswerTest.A1.getWriter()))
            .containsExactly(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));
    }

}