package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class QuestionContents {

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    public QuestionContents() {

    }

    public QuestionContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", title, contents);
    }
}
