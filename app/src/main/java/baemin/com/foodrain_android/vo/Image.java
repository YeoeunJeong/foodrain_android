package baemin.com.foodrain_android.vo;

public class Image {

    /*
     "main_image":{"url" : "http://www.foodrain.co.kr/upload_file/shop/2014_02_.jpg",
	"file_name" : "background.jpg",
	"file_size" : 9992,
	"width" : 600,
	"height" : 400
  }
     */

    private String url;
    private String file_name;
    private int file_size;
    private int width;
    private int height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
