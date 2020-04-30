public class dijkstra {
    Edge map[];// all edge
    Edge solution[];
    int solutionNum = 0;
    int cityNum;//number of city
    Bag scity[][];
    int prev[];
    int dist[];
    int flag[];
    int so[];

    public dijkstra(int n, int m, Edge[] a) {//n=number of all the route,m=number of city,a=the Edge list
        this.map = new Edge[n];
        this.scity = new Bag[m][m];
        int i;
        int j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < m; j++) {
                scity[i][j] = new Bag();
            }
        }
        this.cityNum = m;
        for (i = 0; i < n; i++) {
            scity[a[i].start][a[i].end].addBag(i);
            if (i == 0) {
            }
        }
        for (i = 0; i < n; i++) {
            map[i] = a[i];
        }
        //for(i=0;i<scity[0][1].number;i++){
        //    System.out.println(scity[0][1].edge[i]);
        //}
    }

    public void method(String begintime, int sp, int ep) {//begin time,start point,end point
        String[] temp;
        temp=begintime.split(":");
        int bt = Integer.parseInt(temp[0]);

        int m =10;
        int vs;
        solutionNum = 0;
        this.prev = new int[m];
        this.dist = new int[m];
        this.so = new int[m];
        vs = sp;
        this.flag = new int[m];
        int i;
        for (i = 0; i < m; i++) {
            flag[i] = 0;
            dist[i] = 999;//earliest endtime
            prev[i] = -1;//;last train
        }
        flag[vs] = 1;
        dist[vs] = bt;
        while (vs != -1) {
            int x;
            for (i = 0; i < m; i++) {//renew the point after we hava a new vs
                if (flag[i] == 0) {
                    for (x = 0; x < scity[vs][i].number; x++) {
                        if (map[scity[vs][i].edge[x]].stime >= dist[vs]) {
                            if (map[scity[vs][i].edge[x]].etime < map[scity[vs][i].edge[x]].stime) {
                                map[scity[vs][i].edge[x]].etime = map[scity[vs][i].edge[x]].etime + 24;
                            }
                            if (map[scity[vs][i].edge[x]].etime < dist[i]) {
                                dist[i] = map[scity[vs][i].edge[x]].etime;
                                prev[i] = scity[vs][i].edge[x];
                            }
                        }
                    }
                }
            }
            for (i = 0; i < m; i++) {//find the new vs
                if (flag[i] == 0) {
                    if (flag[vs] != 0) {
                        vs = i;
                    }
                    if (dist[i] < dist[vs]) {
                        vs = i;
                    }
                }
            }
            if (flag[vs] == 0) {//change sign
                flag[vs] = 1;
            } else {
                vs = -1;
            }
        }
        int p = ep;
        int t = 0;
        while (p != sp) {
            p = map[prev[p]].start;
            t++;
        }
        p = ep;
        solutionNum = t;
        solution = new Edge[t];
        while (p != sp) {
            solution[t - 1] = map[prev[p]];
            t--;
            p = map[prev[p]].start;
        }
    }

    public void printSolution() {
        System.out.println(solutionNum);
        for (int i = 0; i < solutionNum; i++) {
            System.out.println("plan number: " + (i + 1));
            System.out.println("get the number: " + solution[i].name);
            System.out.println("place:");
            System.out.print("from " + solution[i].start);
            System.out.println(" to " + solution[i].end);
            System.out.println("time:");
            System.out.print("from " + solution[i].stime);
            System.out.println(" to " + solution[i].etime);
            System.out.println("cost: " + solution[i].money);
            System.out.println();
        }
        System.out.println();
        System.out.println("Have a nice trip!");
    }

    public  int get_start_position(){
        return solution[0].start;
    }

    public String get_start_time(){
        return solution[0].stime + ":00";
    }

    public String get_end_time(){
        return solution[solutionNum-1].etime +":00";
    }

    public String[] get_cities_time(){
        String[] time = new String[solutionNum+1];
        time[0]=solution[0].stime+":00";
        for(int index=0;index<solutionNum;index++){
            time[index+1]=solution[index].etime+":00";
        }
        return time;
    }

    public int[] get_cities(){
        int[] cities = new int[solutionNum+1];
        cities[0]=solution[0].start;
        for(int index=0;index<solutionNum;index++){
            cities[index+1]=solution[index].end;
        }
        return cities;
    }

    public  String[] get_vehicles(){
        String[] vehicles = new String[solutionNum];
        for(int index=0;index<solutionNum;index++){
            vehicles[index]=solution[index].vehicle;
        }
        return vehicles;
    }
    public  int[] get_money(){
        int[] moneys = new int[solutionNum];
        for(int index=0;index<solutionNum;index++){
            moneys[index]=solution[index].money;
        }
        return moneys;
    }
    public String[] get_vehicle_names(){
        String[] vehicle_names = new String[solutionNum];
        for(int index=0;index<solutionNum;index++){
            vehicle_names[index]=solution[index].name;
        }
        return vehicle_names;
    }
}