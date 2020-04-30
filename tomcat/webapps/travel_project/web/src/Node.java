public class Node{
    Edge edge[];
    int number;
    public Node(){
        int i;
        int n=1000;
        this.number=0;
        this.edge=new Edge[n];
        for(i=0;i<n;i++){
            this.edge[i]=new Edge();
        }
    }
    public void addEdge(Edge a){
        number++;
        this.edge[number]=a;
    }
}