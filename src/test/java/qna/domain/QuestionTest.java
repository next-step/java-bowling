package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 자기가_작성한_질문_삭제() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI))
                .hasSize(1);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 다른_사람이_작성한_질문_삭제() {
        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_답변자가_자신인_경우_삭제() throws CannotDeleteException {
        Answer A1 = new Answer(100L, UserTest.SANJIGI, Q2, "contents3");
        Answer A2 = new Answer(101L, UserTest.SANJIGI, Q2, "contents4");
        Q2.addAnswer(A1);
        Q2.addAnswer(A2);

        assertThat(Q2.delete(UserTest.SANJIGI)).hasSize(3);
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    void 답변자가_다른_사람인_경우_삭제() {
        Answer A1 = new Answer(100L, UserTest.SANJIGI, Q2, "contents3");
        Answer A2 = new Answer(101L, UserTest.JAVAJIGI, Q2, "contents4");
        Q2.addAnswer(A1);
        Q2.addAnswer(A2);

        assertThatThrownBy(() -> Q2.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
