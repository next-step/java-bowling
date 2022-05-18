package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 삭제권한(){
        assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void 삭제에대한답변조건() {
        assertThat(Q1.canDeletedAnswerCondition(UserTest.JAVAJIGI)).isFalse();
    }
    @Test
    void 답변이없을경우(){
        Question noAnswerQ = new Question("title3","content3").writeBy(UserTest.JAVAJIGI);

        assertThat(noAnswerQ.canDeletedAnswerCondition(UserTest.JAVAJIGI)).isFalse();
    }

    @Test
    void 질문삭제상태변경() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }
    @Test
    void 다른사용자삭제() throws CannotDeleteException {
        assertThatThrownBy(()->{
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
