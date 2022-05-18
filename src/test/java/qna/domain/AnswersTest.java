package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {


    @Test
    @DisplayName("모든 답변을 삭제합니다.")
    void delete() {
        Answers answers = new Answers(List.of(AnswerTest.A1));
        assertThat(answers.delete(UserTest.JAVAJIGI)).isEqualTo(
                List.of(
                        new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.id,  AnswerTest.A1.getWriter(), LocalDateTime.now()))
                );
    }
}
