package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers();
        answers.add(A1);
        answers.add(A2);
    }

    @Test
    void add() {
        assertThat(answers.answers()).containsExactly(A1, A2);
    }

    @Test
    void 답글_전쳬_삭제() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DeleteHistories deleteHistories = answers.deleteAll(new DeleteHistories(), LocalDateTime.now());

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
        assertThat(deleteHistories.histories()).containsExactly(
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), localDateTime),
                new DeleteHistory(ContentType.ANSWER, A2.getId(), A2.getWriter(), localDateTime)
        );
    }

}