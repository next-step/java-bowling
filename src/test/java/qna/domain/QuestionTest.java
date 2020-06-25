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
    @DisplayName("질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태만 변경한다.")
    void delete_성공() throws CannotDeleteException {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.deleteBy(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 삭제 불가능하다.")
    void delete_사용자_검증_성공() {
        assertThatThrownBy(() -> Q2.deleteBy(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문자와 답변자가 같은 경우 삭제 가능하다.")
    void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        Q1.deleteBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("질문자와 답변자가 다른 경우 삭제 불가능하다.")
    void delete_실패_질문자_답변자_다름() {
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q2.addAnswer(answer);

        assertThatThrownBy(() -> Q2.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
