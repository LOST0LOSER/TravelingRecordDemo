public class ResultJSON {
    public Plan[] plan;
    public ResultJSON() {
        plan = new Plan[3];
    }
    public void set_plan(int index, String start, String end, String[] ve,
                         int[] cit, String[] time,int[] money,String[] v_n){
        this.plan[index] = new Plan(start, end, ve, cit ,time,money,v_n);
    }
}
