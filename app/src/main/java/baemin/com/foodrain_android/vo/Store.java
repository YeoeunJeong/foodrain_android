package baemin.com.foodrain_android.vo;

import java.util.List;

public class Store {
    private int id;
    private String name;
    private String address;
    private int status;
    private String start_time;
    private String end_time;
    private String holiday;
    private String[] tag;
    private int review_cnt;
    private float grade_avg;
    private String phone;
    private List<Menu> menus;
    private List<Image> main_image;


    /*
"address": "서울특별시 송파구 석촌동 666",
"status":1,                          			//배달상태(1:가능, 9:종료)
"start_time":  "11:00",
"end_time":  "23:00",
"holiday":  "월",
"tag":["전화주문"],
"review_cnt”: 3,				// 리뷰수
“grade_avg” 3.2,				// 리뷰 평균 평점
"phone": "02-666-6666",
"menus":
[
{
"uri" : "http://www.foodrain.co.kr/upload_file/shop/2014_02_.jpg",
    }
]
 "main_image":
[
{
"url" : "http://www.foodrain.co.kr/upload_file/shop/2014_02_.jpg",
      }
]
 */
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
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
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Image> getMain_image() {
        return main_image;
    }

    public void setMain_image(List<Image> main_image) {
        this.main_image = main_image;
    }
}
