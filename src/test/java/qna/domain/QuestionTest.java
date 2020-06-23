package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    /* 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다. */
    @DisplayName("삭제할 권한 확인 - 성공")
    @Test
    void checkOwner_성공() {
        assertThatCode(() -> Q1.checkOwnerForDelete(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> Q2.checkOwnerForDelete(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("삭제할 권한 확인 - 실패")
    @Test
    void checkOwner_실패() {
        assertThatThrownBy(() -> Q1.checkOwnerForDelete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");

        assertThatThrownBy(() -> Q2.checkOwnerForDelete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    /*
    * 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
    * 답이 없는 경우 삭제가 가능하다.
    * */
    @DisplayName("삭제 히스토리 생성 체크")
    @Test
    void checkDeleteHistory() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>(Q1.delete(UserTest.JAVAJIGI));
        assertThat(deleteHistories.size()).isEqualTo(1);
        assertThat(deleteHistories.get(0)).isInstanceOf(DeleteHistory.class);
    }

    @DisplayName("삭제 상태 확인")
    @Test
    void checkDelete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>(Q1.delete(UserTest.JAVAJIGI));
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 다른 유저가 답변을 했을경우 질문삭제 불가")
    @Test
    void checkDeleteQuestion_AnswerOtherUser() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문자와 같은유저가 답변을 달았으면 삭제 가능")
    @Test
    void checkDeleteQuestion_AnswerSameUser() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        assertThat(Q1.delete(UserTest.JAVAJIGI).get(0)).isInstanceOf(DeleteHistory.class);
        assertThat(Q1.delete(UserTest.JAVAJIGI).size()).isEqualTo(1);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
