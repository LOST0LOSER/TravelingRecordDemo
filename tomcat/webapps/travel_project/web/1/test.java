import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;
import java.io.*;


public class test{
    public static int readFile(Edge[] timeList) {
        String pathname = "D:\\work\\JAVA\\timelist.txt"; 
        int i=0;
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader);
        ) {
            String line;
            int j=0;
            String name;
            int start;
            int end;
            int money;
            int stime;
            int etime;
            String[] tt;
            String[] temp;
            while ((line = br.readLine()) != null) {
                tt=line.split("\\s+");
                name=tt[0];
                start=Integer.parseInt(tt[1]);
                end=Integer.parseInt(tt[2]);
                temp=tt[3].split(":");
                stime=Integer.parseInt(temp[0]);
                temp=tt[4].split(":");
                etime=Integer.parseInt(temp[0]);
                money=Integer.parseInt(tt[5]);
               /* System.out.println(name);
                System.out.println(start);
                System.out.println(end);
                System.out.println(stime);
                System.out.println(etime);
                System.out.println(money);*/
                timeList[i]=new Edge();
                timeList[i].name=name;
                timeList[i].start=start;
                timeList[i].end=end;
                timeList[i].stime=stime;
                timeList[i].etime=etime;
                timeList[i].money=money;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void main(String[] args) {
        int cnum=10;
        /*Queue<Edge> findWay;
        findWay=new LinkedList<Edge>();
        Edge a=new Edge();
        Edge b=new Edge();
        a.money=2;
        findWay.offer(a);
        b=findWay.poll();
        //System.out.println(b.money);
        Node x=new Node();
        x.addEdge(a);
        //System.out.println(x.edge[0].money);*/
        Edge timeList[]=new Edge[5000];
        int i=readFile(timeList);
        bfs_dp picture=new bfs_dp(i,cnum,timeList);
        picture.method(0,0,9,20);
        picture.printSolution();
    }
}