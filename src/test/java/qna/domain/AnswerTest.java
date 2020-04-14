package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    private User javajigi;
    private Answer answer;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        javajigi = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        answer = new Answer(javajigi, QuestionTest.Q1, "Answers Contents1");
        now = LocalDateTime.of(2019, 4, 11, 1, 52);
    }

    @DisplayName("답변글을 삭제할 수 있다.")
    @Test
    void delete() {
        DeleteHistory expect = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), now);
        DeleteHistory actual = answer.delete(now);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(actual).isEqualTo(expect);
    }
}
