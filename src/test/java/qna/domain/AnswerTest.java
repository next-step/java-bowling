package qna.domain;

import org.junit.jupiter.api.Test;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = Answer.of(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = Answer.of(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 작가는비어있을수없다() {
        assertThatThrownBy(() -> {
            Answer.of(null, QuestionTest.Q1, "내용");
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 질문글은비어있을수없다() {
        assertThatThrownBy(() -> {
            Answer.of(UserTest.SANJIGI, null, "내용");
        }).isInstanceOf(NotFoundException.class);
    }

    @Test
    void 답변글타입반환() {
        assertThat(A1.getContentType()).isEqualTo(ContentType.ANSWER);
    }
}
