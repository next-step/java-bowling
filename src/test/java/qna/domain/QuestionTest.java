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
    @DisplayName("삭제하기 전의 질문 삭제 상태 확인 테스트")
    public void 삭제전_상태_테스트() {
        assertThat(Q1.isDeleted()).isEqualTo(false);
    }

    @Test
    @DisplayName("삭제하기 전의 질문 삭제 상태 확인 테스트")
    public void 질문_작성자_테스트() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    public void 답변_작성자_테스트() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void 삭제후_상태_테스트() {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }
}
