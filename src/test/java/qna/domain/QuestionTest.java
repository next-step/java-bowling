package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3 = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);

    @DisplayName("질문자가 아니면 글 삭제 불가능")
    @Test
    void delete_질문삭제권한_예외() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("답변이 없을 경우, 질문자는 글 삭제 가능")
    @Test
    void delete_본인글삭제() {
        assertThat(Q2.isDeleted()).isFalse();
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @DisplayName("질문자의 글에 본인이 답변을 달면 삭제 가능")
    @Test
    void delete_본인글삭제_답변본인() {
        assertThat(Q3.isDeleted()).isFalse();
        Q3.addAnswer(AnswerTest.A4);
        Q3.delete(UserTest.JAVAJIGI);
        assertThat(Q3.isDeleted()).isTrue();;
    }

    @DisplayName("질문자의 글에 다른 사람이 답변을 달면 삭제 불가능")
    @Test
    void delete_본인글삭제_답변다른사람() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문자가 글을 삭제할 경우, 이력이 남음")
    @Test
    void delete_삭제이력() {
        assertThat(Q1.delete(UserTest.JAVAJIGI).get(0)).isEqualTo(new DeleteHistory(Q1));
    }

}
