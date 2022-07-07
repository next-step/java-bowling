package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deletable() throws CannotDeleteException {
        // 질문만 했을경우
        Assertions.assertThat(Q1.deletable(UserTest.JAVAJIGI)).isTrue();

        // 내가 답변했을 경우
        Q1.addAnswer(AnswerTest.A1);
        Assertions.assertThat(Q1.deletable(UserTest.JAVAJIGI)).isTrue();

        // 남이 답변한 경우
        Q1.addAnswer(AnswerTest.A2);
        Assertions.assertThat(Q1.deletable(UserTest.JAVAJIGI)).isFalse();
    }

    @Test
    void delete() throws CannotDeleteException {
        List<DeleteHistory> delete = Q1.delete(UserTest.JAVAJIGI);
        Assertions.assertThat(delete.contains(Q1));
    }
}
