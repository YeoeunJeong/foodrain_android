package baemin.com.foodrain_android.vo;

import java.util.List;

public class Reviews {
    /*
     "page": 1,
      "total": 1,
      "records": 20,
      "rows": [
        {
          "id": 1,
          "store_id": 1,
          "user": {
            "nickname": "test1",
            "main_image": "/assets/original/missing.png"
          },
          "detail": "테스트 리뷰입니다.",
          "grade": 4.5,
          "created_at": "2016-01-28T08:29:52.269Z",
          "updated_at": "2016-01-28T08:29:52.269Z",
          "images": []
        },
     */
    private int page;
    private int total;
    private int records;
    private List<Review> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<Review> getRows() {
        return rows;
    }

    public void setRows(List<Review> rows) {
        this.rows = rows;
    }
}
