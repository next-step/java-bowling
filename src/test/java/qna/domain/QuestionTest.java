package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {

    public Question Q1;
    public Question Q2;

    @BeforeEach
    void init (){
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @DisplayName("Question을 삭제하면 데이터상태가 삭제상태인 true로 변한다.")
    @Test
    void testCase1() {
        Question question = Q1.delete();

        assertThat(question.isDeleted()).isEqualTo(true);
        assertThat(Q2.isDeleted()).isEqualTo(false);
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    @Test
    void testCase2() {
        Question question = Q1.delete(UserTest.JAVAJIGI);
        Question question2 = Q2.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isEqualTo(true);
        assertThat(question2.isDeleted()).isEqualTo(false);
    }

    @DisplayName("답변이 없는 경우 삭제가 가능하다.")
    @Test
    void testCase3() {
        Q2.addAnswer(new Answer());
        Question question = Q1.delete(UserTest.JAVAJIGI);
        Question question2 = Q2.delete(UserTest.SANJIGI);

        assertThat(question.isDeleted()).isEqualTo(true);
        assertThat(question2.isDeleted()).isEqualTo(false);
    }
}
