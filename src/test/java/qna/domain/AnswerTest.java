package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("인자로 받은 로그인 사용자가 답변 작성자와 일치할 경우 삭제 이력을 반환한다.")
    public void delete() {
        //given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "test");

        //when
        DeleteHistory delete = answer.delete(UserTest.JAVAJIGI);

        then(delete).isEqualTo(DeleteHistory.ofAnswer(answer.getId(), answer.getWriter()));
    }

    @Test
    @DisplayName("삭제 성공 시, 삭제 상태를 참으로 변환한다.")
    public void isDeleted() {
        //given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "test");

        //when
        answer.delete(UserTest.JAVAJIGI);

        then(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("인자로 받은 로그인 사용자가 답변 작성자와 일치하지 않을 경우 예외가 발생한다.")
    public void delete_by_different_writer() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "test");
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
