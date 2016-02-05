package baemin.com.foodrain_android.vo;

public class UserWithAccessToken {
/*
 "access_token": "sqbkktg3s00vzz7gg3s198rzb9g3s2me2u2ng3s3",
  "user": {
    "id": 1,
    "email": "leetaejun147@gmail.com",
    "password": "leetaejun147",
    "nickname": "태두니",
    "phone": "01087711202",
    "gender": 1,
    "images": {
      "url": "host/default.png"
    }
  }
 */

    private String access_token;
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
