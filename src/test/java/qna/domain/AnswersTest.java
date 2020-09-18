package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    @DisplayName("전체 삭제")
    public void deleteAll() throws CannotDeleteException {
        Answers answers = new Answers(Collections.singletonList(AnswerTest.A1));
        assertThat(answers.deleteAllAnswers(AnswerTest.A1.getWriter()))
                .containsExactly(new DeleteHistory(
                        ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(),
                        LocalDateTime.now()
                ));
    }

    @Test
    @DisplayName("전체 삭제 예외 - 다른 사람 답변 존재")
    public void hasExistMessage() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThatThrownBy(() -> answers.deleteAllAnswers(AnswerTest.A1.getWriter()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
