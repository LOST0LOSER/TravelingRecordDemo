import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class test{
    public static int readFile(Edge[] timeList) {
        String pathname = "/time.txt";
        int i = 0;
        pathname = test.class.getResource(pathname).getPath();
        try {
            FileReader reader = new FileReader(pathname);
            BufferedReader br = new BufferedReader(reader);

            String line;
            String name;
            int start;
            int end;
            int money;
            int stime;
            int etime;
            String vehicle = null;
            String[] tt;
            String[] temp;
            while ((line = br.readLine()) != null) {
                tt = line.split("\\s+");
                name = tt[0];
                start = Integer.parseInt(tt[1]);
                end = Integer.parseInt(tt[2]);
                temp = tt[3].split(":");
                stime = Integer.parseInt(temp[0]);
                temp = tt[4].split(":");
                etime = Integer.parseInt(temp[0]);
                money = Integer.parseInt(tt[5]);
                if (tt[6] != null) {
                    vehicle = tt[6];
                }
                /*
                System.out.println(name);
                System.out.println(start);
                System.out.println(end);
                System.out.println(stime);
                System.out.println(etime);
                System.out.println(money);
                System.out.println(vehicle);
                 */
                timeList[i] = new Edge();
                timeList[i].name = name;
                timeList[i].start = start;
                timeList[i].end = end;
                timeList[i].stime = stime;
                timeList[i].etime = etime;
                timeList[i].money = money;
                timeList[i].vehicle = vehicle;
                i++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void main(String[] args) {
        int cnum = 10;
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
        Edge timeList[] = new Edge[5000];
        int i = readFile(timeList);
        bfs_dp picture = new bfs_dp(i, cnum, timeList);
        picture.method("00:00", 0, 9, "20:00");
        picture.printSolution();
    }
}