public class Bag{
    int edge[];
    int number;
    public Bag(){
        int i;
        int n=1000;
        this.number=0;
        this.edge=new int[n];
        for(i=0;i<n;i++){
            this.edge[i]=-1;
        }
    }
    public void addBag(int a){
        this.edge[number]=a;
        number++;
    }
}