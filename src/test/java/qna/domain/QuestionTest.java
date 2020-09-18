package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void 본인이_쓴_질문_삭제_성공() throws Exception {
        Q1.deleteQna(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();

        Q2.deleteQna(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test(expected = CannotDeleteException.class)
    public void 타인이_쓴_질문_삭제_실패() throws Exception {
        Q1.deleteQna(UserTest.SANJIGI);
        assertThat(Q1.isDeleted()).isFalse();

        Q2.deleteQna(UserTest.JAVAJIGI);
        assertThat(Q2.isDeleted()).isFalse();
    }
}
