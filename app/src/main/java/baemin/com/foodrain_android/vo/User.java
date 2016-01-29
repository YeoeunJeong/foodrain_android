package baemin.com.foodrain_android.vo;

public class User {

    /*
    "user": {
        "nickname": "test1",
        "main_image": "/assets/original/missing.png"
      },
     */

    private String nickname;
    private String main_image;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }
}
