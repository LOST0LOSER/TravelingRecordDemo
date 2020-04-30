/**
 * json对象转换为java对象
 * 
 *
 */
public class GetJSON {//从前端获取的数据
    //编译器自己弄构造函数
    private String start_time;
    private int start_position;
    private int destination;
    private String limited_time;
    public GetJSON(){
        start_time=null;
        start_position=0;
        destination=0;
        limited_time=null;
    }
    public void testdata() {
        System.out.println(this.get_start_position());
        System.out.println(this.get_destination());
        System.out.println(this.get_start_time());
        System.out.println(this.get_limited_time());
    }

    //获取数据
    public String get_start_time(){
        return start_time;
    }
    public int get_start_position(){
        return start_position;
    }
    public int get_destination(){
        return destination;
    }
    public String get_limited_time(){
        return limited_time;
    }
}