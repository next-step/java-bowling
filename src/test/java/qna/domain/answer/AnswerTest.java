package qna.domain.answer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.QuestionTest;
import qna.domain.UserTest;
import qna.domain.answer.Answer;
import qna.error.CannotDeleteException;
import qna.error.MatchingQuestionUserAndAnswerUserException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("생성 테스트")
    void createTest(){
        Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q2, "Answer Failed");
        Assertions.assertThat(A3.getWriter()).isEqualTo(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest(){
        A1.delete(UserTest.JAVAJIGI);

        Assertions.assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른 사람이 쓴 댓글의 경우 삭제가 불가능")
    void invalidDeleteTest(){
        Assertions.assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
          .isInstanceOf(MatchingQuestionUserAndAnswerUserException.class);
    }
}
