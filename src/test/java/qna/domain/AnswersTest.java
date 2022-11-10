package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

class AnswersTest {
    public static final List<Answer> SAME_OWNER = List.of(answer(UserTest.JAVAJIGI, UserTest.JAVAJIGI), answer(UserTest.JAVAJIGI, UserTest.JAVAJIGI));
    public static final List<Answer> DIFFERENT_OWNER = List.of(answer(UserTest.JAVAJIGI, UserTest.JAVAJIGI), answer(UserTest.JAVAJIGI, UserTest.SANJIGI));
    
    @DisplayName("답변 소유자 외에 다른 사람이 작성한 답변이 없다면 답변 삭제상태를 true 로 바꾸고 삭제 히스토리를 리턴한다.")
    @Test
    void delete() throws CannotDeleteException {
        assertThat(new Answers(SAME_OWNER).delete(UserTest.JAVAJIGI))
                .containsExactlyElementsOf(List.of(
                        new DeleteHistory(ContentType.ANSWER, SAME_OWNER.get(0).getId(), SAME_OWNER.get(0).getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, SAME_OWNER.get(1).getId(), SAME_OWNER.get(1).getWriter(), LocalDateTime.now())
                ));
        assertThat(SAME_OWNER).allSatisfy((Answer answer) -> answer.isDeleted());
    }

    @DisplayName("답변 소유자외에 다른 사람이 작성한 답변이 있다면 예외를 발생시킨다.")
    @Test
    void delete_when_invalid_owner() {
        assertThatThrownBy(() -> new Answers(DIFFERENT_OWNER).delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    public static Answer answer(long contentId, User questionWriter, User answerWriter) {
        return new Answer(contentId, answerWriter, QuestionTest.question(123L, questionWriter), "contents");
    }
    
    public static Answer answer(User questionWriter, User answerWriter) {
        return new Answer(answerWriter, QuestionTest.question(123L, questionWriter), "contents");
    }
}
