package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문을 작성한 사용자가 아니기 때문에 삭제 불가 테스트")
    @Test
    void deletable_fail() {
        assertThatThrownBy(() -> Q1.deletable(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문의 정상적 삭제 확인")
    @Test
    void delete() {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }
}
