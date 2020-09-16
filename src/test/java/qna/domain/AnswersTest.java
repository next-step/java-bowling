package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    @DisplayName("전체 삭제 테스트")
    @Test
    public void deleteAllTest() {
        Answers answers = new Answers(Collections.singletonList(AnswerTest.A1));
        assertThat(answers.deleteAll(AnswerTest.A1.getWriter()))
                .containsExactly(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));
    }

    @DisplayName("전체 삭제 예외처리 테스트")
    @Test
    public void deleteAllExceptionTest() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThatThrownBy(() -> answers.deleteAll(AnswerTest.A1.getWriter()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
