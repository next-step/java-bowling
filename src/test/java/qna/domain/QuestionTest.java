package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 다른_사람의_질문_삭제() {
        assertThatThrownBy(() -> {
            Q1.delete(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 자신의_질문_삭제() {
        Q1.delete(Q1.getWriter());
    }
}
