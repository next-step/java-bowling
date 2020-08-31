package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("다른 사람이 쓴 질문일 경우 삭제 시 예외")
    void testTryDeleteOtherUser() {
        assertThatThrownBy(() -> Q1.deleteByUser(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자가 쓴 질문일 경우 삭제")
    void testTryDeleteWriteUser() {
        Q1.deleteByUser(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
