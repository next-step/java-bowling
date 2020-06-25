package qna.domain;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void 글쓴이가_아니거나_다른사람_댓글이_있으면_삭제불가() {
        assertThatThrownBy(() -> Q1.checkEnableDelete(UserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
