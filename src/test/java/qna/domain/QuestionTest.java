package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.AnswersTest.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    @DisplayName("질문 소유자와 로그인 한 사용자가 같고, 답변 소유자 외에 다른 사용자가 작성한 답변이 없다면 질문 삭제상태를 true 로 바꾸고 삭제 이력을 리턴한다.")
    @Test
    void delete() throws CannotDeleteException {
        long id = 123L;
        Question question = question(id, UserTest.JAVAJIGI, UserTest.JAVAJIGI, UserTest.JAVAJIGI);
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
        Question question = question(123L, UserTest.JAVAJIGI, UserTest.JAVAJIGI, UserTest.SANJIGI);
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class).hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문 소유자와 로그인 한 사용자가 다르다면 CannotDeleteException 예외를 발생시킨다.")
    @Test
    void delete_when_invalid_question_owner() {
        Question question = question(123L, UserTest.JAVAJIGI, UserTest.JAVAJIGI, UserTest.JAVAJIGI);
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class).hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    public static Question question(long contentId, User writer) {
        Question question = new Question("title", "contents").writeBy(writer);
        question.setId(contentId);
        return question;
    }

    private static Answers answers(User questionWriter, User... answerWriters) {
        return new Answers(Arrays.stream(answerWriters).map(writer -> answer(questionWriter, writer)).collect(Collectors.toList()));
    }
    
    private static Question question(long contentId, User writer, User... answerWriters) {
        Question question = new Question("title", "contents", answers(writer, answerWriters)).writeBy(writer);
        question.setId(contentId);
        return question;
    }
}
