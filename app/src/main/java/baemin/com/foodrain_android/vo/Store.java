package baemin.com.foodrain_android.vo;

import java.util.List;

public class Store {

    private int id;
    private String name;
    private String address;
    private int status;
    private String[] tag;
    private int review_cnt;
    private float grade_avg;
    private List<Image> main_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public int getReview_cnt() {
        return review_cnt;
    }

    public void setReview_cnt(int review_cnt) {
        this.review_cnt = review_cnt;
    }

    public float getGrade_avg() {
        return grade_avg;
    }

    public void setGrade_avg(float grade_avg) {
        this.grade_avg = grade_avg;
    }

    public List<Image> getMain_image() {
        return main_image;
    }

    public void setMain_image(List<Image> main_image) {
        this.main_image = main_image;
    }
}
