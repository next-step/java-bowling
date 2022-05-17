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
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));
        assertThat(answers.delete()).isEqualTo(
                new DeleteHistories(List.of(
                        new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.id,  AnswerTest.A1.getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.id,  AnswerTest.A2.getWriter(), LocalDateTime.now()
                     ))
                ));
    }
}
