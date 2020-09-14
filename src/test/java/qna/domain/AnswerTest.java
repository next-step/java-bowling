package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("answer 삭제하기")
    void delete() throws CannotDeleteException {
        // given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi");
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, LocalDateTime.now());

        // when
        DeleteHistory result = answer.delete();

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(answer.isDeleted()).isTrue();

    }

}
