package baemin.com.foodrain_android.vo;

import java.util.List;

public class Review {

    /*
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
     */

    private int id;
    private int store_id;
    private User user;
    private String detail;
    private float grade;
    private String created_at;
    private String updated_at;
    private List<Image> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
