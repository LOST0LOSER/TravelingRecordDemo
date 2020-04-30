import java.util.Queue;
import java.util.LinkedList;

public class bfs_dp{
    Edge map[];// all edge
    Edge solution[];
    int solutionNum;
    Node city[];// ranked edge
    int cityNum;//number of city
    int mlist[][];// money list ,which time at which city we need to cost
    int hispoint[][];// for one point in money list, its previous point(where it is from)
    int histime[][];// how many time it costs to gethere(from hispoint)
    int hisedge[][];
    int total;//number of all edge
    int s[];//sign of whether the city is in the queue, 1 is in, 0 is out
    int maxtime;// the limited time add begin time(if we begin at 2 , limited is 5, then maxtime is 7)
    int mint;//the min time when go through the method(the answer)
    int minm;// the min money
    public bfs_dp(int n,int m,Edge[] a){//n=number of all the route,m=number of city,a=the Edge list
        this.map=new Edge[n];
        this.city=new Node[m];
        this.total=n;
        this.cityNum=m;
        this.maxtime=100;
        this.mlist=new int[m][maxtime+1];
        this.hispoint=new int[m][maxtime+1];
        this.histime=new int[m][maxtime+1];
        this.hisedge=new int[m][maxtime+1];
        this.s=new int[m];
        this.mint=-1;
        this.minm=-1;
        int i;
        int j;
        for(i=0;i<m;i++){
            for(j=0;j<=maxtime;j++){
                mlist[i][j]=-1;
                hispoint[i][j]=-1;
                histime[i][j]=-1;
                hisedge[i][j]=-1;
            }
        }
        for(i=0;i<n;i++){
            map[i]=a[i];
        }
        for(i=0;i<m;i++){
            s[i]=0;
            city[i]=new Node();
        }
        for(i=0;i<n;i++){
            city[a[i].start].addEdge(a[i]);
        }
    }
    public void method(int bt,int sp,int ep,int mt){//begin time,start point,end point and limited time
        maxtime=mt+bt;
        Queue<Integer> findWay;
        findWay=new LinkedList<Integer>();//creat a queue "findWay"
        int i;
        int now;
        findWay.offer(sp);
        mlist[sp][bt]=0;//for first point, we don't need to spend money,so is 0.it is at "startpoint" and the time is "begin time"
        s[sp]=1;//city "sp" is in the queue
        System.out.println("time now: "+bt);
        System.out.println("limit time: "+maxtime);
        System.out.println("start point: "+sp);
        System.out.println("end point: "+ep);
        System.out.println();
        while(findWay.peek()!=null){//if the queue is not empty
            now=findWay.poll();//poll out the first city
            s[now]=0;
            for(i=1;i<=city[now].number;i++){//check all the edge start from that city
                int to=city[now].edge[i].end;//destination
                int st=city[now].edge[i].stime;//starttime
                if(st<bt){
                    st=st+24;
                }
                int et=city[now].edge[i].etime;//end time
                if(st>et){
                    et=et+24;
                }
                int c=city[now].edge[i].money;// cost
                //System.out.print("place: from "+now);
                //System.out.print(" to "+to);
                //System.out.print(" -- time: from "+st);
               // System.out.print(" to "+et);
                //System.out.println(" -- cost: "+c);
                if(et<=maxtime){// if it's "end time" doesn't above the "max time", we can go that edge
                    for(int j=bt;j<=st;j++){
                        if(mlist[now][j]!=-1&&((mlist[now][j]+c<mlist[to][et])||mlist[to][et]==-1)){
                            int bs=0;
                            for(int k=bt;k<=j;k++){
                                if(mlist[to][k]!=-1&&mlist[to][k]<=mlist[now][j]+c){
                                    bs=1;
                                }
                            }
                            if(bs==0){
                                //System.out.print("place: from "+now);
                                //System.out.print(" to "+to);
                                //System.out.print(" -- time: from "+st);
                                //System.out.print(" to "+et);
                                //System.out.println(" -- cost: "+c);
                                mlist[to][et]=mlist[now][j]+c;//the money we get the city "to" at "et"(endtime) 
                                hispoint[to][et]=now;         //is the money we already cost in the city"now"
                                histime[to][et]=j;            //at time "j" plus the cost of the route.
                                hisedge[to][et]=i;
                                if(s[to]!=1){                 //Because "j"<"st"(starttime), we don't miss the train,so we can go that edge
                                   s[to]=1;                  //when we renew a point, offer it to the queue
                                   findWay.offer(to);
                                }
                            }
                        }
                        //System.out.println("to point: "+to);
                        //System.out.println("cost: "+mlist[to][et]);
                    }
                }
            }
        }
        for(i=bt;i<=maxtime;i++){ //get the answer from the money list
            if(minm == -1){
                minm = mlist[ep][i];
                mint = i;
            }
		    else if(mlist[ep][i] != -1 && minm > mlist[ep][i]){
                minm = mlist[ep][i];
                mint = i;
            }
        }
        int p=ep;
        int t=mint;
        int tempt,tempp;
        int count=0;
        while(t!=bt){
            count++;
            tempt=histime[p][t];
            tempp=hispoint[p][t];
            t=tempt;
            p=tempp;
        }
        solution=new Edge[count];
        solutionNum=count;
        p=ep;
        t=mint;
        while(t!=bt){
            count--;
            solution[count]=city[hispoint[p][t]].edge[hisedge[p][t]];
            tempt=histime[p][t];
            tempp=hispoint[p][t];
            t=tempt;
            p=tempp;
        }
    }
    public void printSolution(){
        for(int i=0;i<solutionNum;i++){
            System.out.println("plan number: "+(i+1));
            System.out.println("get the number: "+solution[i].name);
            System.out.println("place:");
            System.out.print("from "+solution[i].start);
            System.out.println(" to "+solution[i].end);
            System.out.println("time:");
            System.out.print("from "+solution[i].stime);
            System.out.println(" to "+solution[i].etime);
            System.out.println("cost: "+solution[i].money);
            System.out.println();
        }
        System.out.println("total time: "+mint);
        System.out.println("total cost: "+minm);
        System.out.println();
        System.out.println("Have a nice trip!");
    }
}