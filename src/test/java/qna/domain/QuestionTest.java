package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.AnswersTest.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 소유자와 로그인 한 사용자가 같고, 답변 소유자 외에 다른 사용자가 작성한 답변이 없다면 질문 삭제상태를 true 로 바꾸고 삭제 이력을 리턴한다.")
    @Test
    void delete() throws CannotDeleteException {
        long id = 123L;
        Question question = Q1.clone(id).addAnswers(SAME_OWNER);
        List<DeleteHistory> histories = question.delete(UserTest.JAVAJIGI);
        
        assertThat(question.isDeleted()).isTrue();
        assertThat(histories).hasSameElementsAs(List.of(
                new DeleteHistory(ContentType.QUESTION, id, UserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, SAME_OWNER.get(0).getId(), SAME_OWNER.get(0).getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, SAME_OWNER.get(1).getId(), SAME_OWNER.get(1).getWriter(), LocalDateTime.now())
        ));
    }

    @DisplayName("답변 소유자 외에 다른 사용자가 작성한 답변이 있다면 CannotDeleteException 예외를 발생시킨다.")
    @Test
    void delete_when_invalid_answer_owner() {
        Question question = Q1.clone(123L).addAnswers(DIFFERENT_OWNER);
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class).hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문 소유자와 로그인 한 사용자가 다르다면 CannotDeleteException 예외를 발생시킨다.")
    @Test
    void delete_when_invalid_question_owner() {
        assertThatThrownBy(() -> Q1.delete(Q2.getWriter())).isInstanceOf(CannotDeleteException.class).hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
