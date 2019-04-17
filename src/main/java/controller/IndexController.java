package controller;

import static spark.Spark.get;

public class IndexController extends AbstractController {

    public void index() {
        get("/", (req, res) -> renderIndex());
    }

    private Object renderIndex() {
        return render(null, INDEX_TEMPLATE_PATH);
    }
}