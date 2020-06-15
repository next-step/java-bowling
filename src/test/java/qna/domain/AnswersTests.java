package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1J;
import static qna.domain.AnswerTest.A1S;
import static qna.domain.AnswerTest.A2J;

public class AnswersTests {
    private List<Answer> twoAnswerListSameWithQuestion = Arrays.asList(A1J, A2J);
    private List<Answer> twoAnswerListDifferentWithQuestion = Arrays.asList(A1J, A1S);
    private List<Answer> emptyAnswerList = new ArrayList<>();

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
    public void deleteTest() {
        assertThat(A1J.isDeleted()).isFalse();
        assertThat(A2J.isDeleted()).isFalse();

        Answers answers = new Answers(twoAnswerListSameWithQuestion);
        answers.delete();

        assertThat(A1J.isDeleted()).isTrue();
        assertThat(A2J.isDeleted()).isTrue();
    }
}
