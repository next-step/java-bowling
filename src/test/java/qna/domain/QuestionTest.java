package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("작성자가 아닌 다른 사람이 삭제 요청을 했을시 예외처리 발생 여부 테스트")
    @Test
    void 예외_다른_작성자() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");

        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("삭제가 되었을 때 상태값이 true 로 변경되었는지 테스트")
    @Test
    void 변환_삭제상태() throws CannotDeleteException {
        // when
        Q1.delete(UserTest.JAVAJIGI);
        Q2.delete(UserTest.SANJIGI);

        // then
        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(Q2.isDeleted()).isTrue()
        );
    }

}
