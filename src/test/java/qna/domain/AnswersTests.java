package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

public class AnswersTests {
    private List<Answer> twoAnswerList = Arrays.asList(A1, A2);
    private List<Answer> emptyAnswerList = new ArrayList<>();

    @DisplayName("Answer 콜렉션을 통해 객체를 생성할 수 있다.")
    @Test
    public void createTest() {
        assertThat(twoAnswerList.size()).isEqualTo(2);
        Answers answers = new Answers(twoAnswerList);

        assertThat(answers).isNotNull();
        assertThat(answers.size()).isEqualTo(2);
    }

    @DisplayName("속한 질문들을 삭제 처리할 수 있다.")
    @Test
    public void deleteTest() {
        assertThat(A1.isDeleted()).isFalse();
        assertThat(A2.isDeleted()).isFalse();

        Answers answers = new Answers(twoAnswerList);
        answers.delete();

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }
}
