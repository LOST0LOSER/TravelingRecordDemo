public class Edge{
    String name;
    int start;
    int end;
    int money;
    int stime;
    int etime;
    public Edge(){
        this.money=-1;
        this.name="";
        this.start=-1;
        this.stime=-1;
        this.etime=-1;
        this.end=-1;
    }
    public Edge(String n,int s, int e, int m,int ts,int te){
        this.money=m;
        this.name=n;
        this.start=s;
        this.stime=ts;
        this.etime=te;
        this.end=e;
    }
}