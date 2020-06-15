package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTests {
    private List<Answer> twoAnswerList = new ArrayList<>();
    private List<Answer> emptyAnswerList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        twoAnswerList.add(AnswerTest.A1);
        twoAnswerList.add(AnswerTest.A2);
    }

    @DisplayName("Answer 콜렉션을 통해 객체를 생성할 수 있다.")
    @Test
    public void createTest() {
        assertThat(new Answers(twoAnswerList)).isNotNull();
    }
}
