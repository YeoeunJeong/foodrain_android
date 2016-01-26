package baemin.com.foodrain_android.vo;

public class Category {
    /*{"id":1,
 "name": "치킨"
},
 {"id":2,
 "name":"중국집"
},
{"id":3,
 "name":"피자"
},
{"id":4,
 "name":"족발/보쌈"
}
*/
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
