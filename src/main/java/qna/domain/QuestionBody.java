package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class QuestionBody {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    public QuestionBody() {
    }

    public QuestionBody(String title, String contents) {
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
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        if(obj.getClass() == getClass()) {
            QuestionBody questionBody = (QuestionBody) obj;
            return title.equals(questionBody.title)
                && contents.equals(questionBody.contents);
        }

        return false;
    }
}
