package baemin.com.foodrain_android.vo;

import java.util.List;

public class Stores {
/*
"page": 1,
  "total": 2,
  "records": 20,
  "rows": [
   */

    private int page;
    private int total;
    private int records;
    private List<Store> rows;

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

    public List<Store> getRows() {
        return rows;
    }

    public void setRows(List<Store> rows) {
        this.rows = rows;
    }
}
