package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
    public static final DeleteHistory H1 = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());
    public static final DeleteHistory H2 = new DeleteHistory(ContentType.ANSWER, A2.getId(), A2.getWriter(), LocalDateTime.now());

    @Test
    void 다른_사람의_답변_삭제() {
        assertThatThrownBy(() -> {
            A1.delete(A2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 본인의_답변_삭제() {
        A1.delete(A1.getWriter());
    }

    @Test
    void 답변_삭제시_기록() {
        assertThat(A1.createRecord()).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }
}
