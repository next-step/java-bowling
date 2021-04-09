package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void init() {
        Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
        Answer A2 = new Answer(UserTest.SANJIGI, Q2, "Answers Contents2");
        answers = Answers.of(Arrays.asList(A1,A2));
    }

    @DisplayName("질문자와 답변글이 같은지 판별한다.")
    @Test
    void testCase1() {
        User user = UserTest.JAVAJIGI;
        Answers answers = createAnswers(UserTest.JAVAJIGI, UserTest.JAVAJIGI);
        boolean owner = answers.isOwner(user);

        assertThat(owner).isTrue();
    }

    @DisplayName("로그인 사용자와 답변한 사람이 같지 않으면 예외가 발생한다.")
    @Test
    void testCase2() {
        User user = UserTest.JAVAJIGI;
        Answers answers = createAnswers(UserTest.JAVAJIGI, UserTest.SANJIGI);
        assertThatThrownBy(() ->{
            answers.isOwner(user);
        }).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("답변들이 삭제되면 답변들을 삭제 히스토리에 저장한다.")
    @Test
    void testCase4() {
        Answers answers = createAnswers(UserTest.JAVAJIGI, UserTest.SANJIGI);
        DeleteHistorys delete = answers.delete(DeleteHistorys.of());

        assertThat(delete.size()).isEqualTo(2);
    }

    Answers createAnswers(User user, User user2) {
        Question Q1 = new Question("title1", "contents1").writeBy(user);
        Answer A1 = new Answer(user, Q1, "Answers Contents1");
        Answer A2 = new Answer(user2, Q1, "Answers Contents2");

        return Answers.of(Arrays.asList(A1, A2));
    }
}