package qna.domain;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @BeforeEach
    public void init() {
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI,Q1,"contents1"));
        Q2.addAnswer(new Answer(UserTest.JAVAJIGI,Q2,"contents2"));
    }

    @Test
    @DisplayName("질문 작성자가 아닐 때의 삭제 불가 테스트")
    public void 질문_작성자_테스트() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("답변 작성자 중에 질문 작성자가 아닌 사람이 있을 때의 삭제 불가 테스트")
    public void 답변_작성자_테스트() {
        assertThatThrownBy(() -> Q2.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("삭제 전후의 질문 삭제 상태 확인 테스트")
    public void 삭제전후_상태_테스트() {
        assertThat(Q1.isDeleted()).isEqualTo(false);
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }
}
