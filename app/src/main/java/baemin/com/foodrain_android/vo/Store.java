package baemin.com.foodrain_android.vo;

public class Store {

    /*
    [{"id":1,
 "name":네네치킨 석촌점",
 "address": "서울특별시 송파구 석촌동 666"
 "status":1,                          	//배달상태(1:가능, 9:종료)
 "tag":["전화주문", "바로결제"]
 "review”: 3
 “grade” 3.2
 "main_image":{"url" : "http://www.foodrain.co.kr/upload_file/shop/2014_02_.jpg",
	"file_name" : "background.jpg",
	"file_size" : 9992,
	"width" : 600,
	"height" : 400
  }
}]
     */

    private int id;
    private String name;
    private String address;
    private int status;
    private String[] tag;
    private int review;
    private float grade;
    private Image mainImage;

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

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Image getMainImage() {
        return mainImage;
    }

    public void setMainImage(Image mainImage) {
        this.mainImage = mainImage;
    }
}
