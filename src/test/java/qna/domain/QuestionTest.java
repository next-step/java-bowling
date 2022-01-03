package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_성공() throws CannotDeleteException {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
