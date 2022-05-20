package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.ContentType.ANSWER;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 자신이_작성한_질문_삭제() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void 자신이_작성하지_않은_질문_삭제() {
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
