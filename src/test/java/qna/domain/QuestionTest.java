package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(1, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2, "title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question q1;

    public static Question javajigiQuestion() {
        return new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @BeforeEach
    void setUp() {
        q1 = javajigiQuestion();
    }

    @Test
    @DisplayName("로그인한 유저가 질문자가 아니면 삭제할 수 없다.")
    void shouldNotDeleteQuestionIfLoggedInUserIsNotOwnThat() {
        assertThatThrownBy(() -> q1.beDeletedBy(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변글 중 본인이 작성한 것이 아닌 답변글이 있으면 삭제할 수 없다.")
    void shouldNotDeleteQuestionIfAnswerIsNotMine() {
        q1.addAnswer(AnswerTest.sanjigiAnswer());
        assertThatThrownBy(() -> q1.beDeletedBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문한 사람이 로그인한 사람과 같다면 삭제할 수 있다.")
    void canDeleteQuestionIfUserOwnQuestion() {
        assertThat(q1.beDeletedBy(UserTest.JAVAJIGI)).containsExactly(DeleteHistory.of(q1));
    }

    @Test
    @DisplayName("모든 답변글이 로그인한 사람이 작성한 것이면 삭제할 수 있다.")
    void canDeleteQuestionIfAllAnswersOfQuestionIsMine() {
        Answer answer = AnswerTest.javajigiAnswer();
        q1.addAnswer(answer);

        assertThat(q1.beDeletedBy(UserTest.JAVAJIGI)).contains(DeleteHistory.of(q1), DeleteHistory.of(answer));
    }

}
