package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(1, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2, "title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3;
    public static final Question Q4;

    static {
        Q3 = new Question(3, "title3", "contents3").writeBy(UserTest.JAVAJIGI);
        Q3.addAnswer(AnswerTest.A2);

        Q4 = new Question(4, "title4", "contents4").writeBy(UserTest.JAVAJIGI);
        Q4.addAnswer(AnswerTest.A1);
    }

    @Test
    @DisplayName("로그인한 유저가 질문자가 아니면 삭제할 수 없다.")
    void shouldNotDeleteQuestionIfLoggedInUserIsNotOwnThat() {
        assertThatThrownBy(() -> Q1.beDeletedBy(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변글 중 본인이 작성한 것이 아닌 답변글이 있으면 삭제할 수 없다.")
    void shouldNotDeleteQuestionIfAnswerIsNotMine() {
        assertThatThrownBy(() -> Q3.beDeletedBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문한 사람이 로그인한 사람과 같다면 삭제할 수 있다.")
    void canDeleteQuestionIfQuestionIsMine() {
        assertThat(Q1.beDeletedBy(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @Test
    @DisplayName("모든 답변글이 로그인한 사람이 작성한 것이면 삭제할 수 있다.")
    void canDeleteQuestionIfAllAnswersOfQuestionIsMine() {
        assertThat(Q4.beDeletedBy(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 히스토리를 생성한다.")
    void createDeleteHistories() {
        Question deletedQuestion = Q4.beDeletedBy(UserTest.JAVAJIGI);

        List<DeleteHistory> expected = Arrays.asList(
            new DeleteHistory(ContentType.QUESTION, deletedQuestion.getId(), deletedQuestion.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), deletedQuestion.getWriter(), LocalDateTime.now())
        );

        assertThat(deletedQuestion.createDeleteHistories()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("삭제된 질문이 아니면 히스토리를 만들 수 없다.")
    void cannotCreateDeleteHistoriesIfQuestionIsNotDeleted() {
        assertThatThrownBy(Q2::createDeleteHistories).isInstanceOf(RuntimeException.class);
    }

}
