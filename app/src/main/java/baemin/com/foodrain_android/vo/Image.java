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
    private String fileName;
    private int fileSize;
    private int width;
    private int height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
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
