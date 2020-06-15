package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.*;

public class AnswersTests {
    private List<Answer> twoAnswerListSameWithQuestion = new ArrayList<>();
    private List<Answer> twoAnswerListDifferentWithQuestion = new ArrayList<>();

    @Before
    public void setup() {
        twoAnswerListSameWithQuestion.add(A1J);
        twoAnswerListSameWithQuestion.add(A2J);
        twoAnswerListDifferentWithQuestion.add(A3J);
        twoAnswerListDifferentWithQuestion.add(A1S);
    }

    @DisplayName("Answer 콜렉션을 통해 객체를 생성할 수 있다.")
    @Test
    public void createTest() {
        assertThat(twoAnswerListSameWithQuestion.size()).isEqualTo(2);
        Answers answers = new Answers(twoAnswerListSameWithQuestion);

        assertThat(answers).isNotNull();
        assertThat(answers.size()).isEqualTo(2);
    }

    @DisplayName("답변의 작성자가 모두 질문자인 경우 전체 삭제가 가능하다.")
    @Test
    public void deleteTest() throws CannotDeleteException {
        assertThat(A1J.isDeleted()).isFalse();
        assertThat(A2J.isDeleted()).isFalse();

        Answers answers = new Answers(twoAnswerListSameWithQuestion);
        answers.delete(UserTest.JAVAJIGI);

        assertThat(A1J.isDeleted()).isTrue();
        assertThat(A2J.isDeleted()).isTrue();
    }

    @DisplayName("답변의 작성자 중 한명이라도 질문자와 다른 경우 예외 발생 - CannotDeleteException")
    @Test
    public void deleteValidationTest() {
        Answers answers = new Answers(twoAnswerListDifferentWithQuestion);

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
