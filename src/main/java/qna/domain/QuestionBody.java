package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class QuestionBody {
    public QuestionBody() {
    }

    public QuestionBody(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;
}
