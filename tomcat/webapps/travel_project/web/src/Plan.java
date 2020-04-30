public class Plan {
    private String start_time;
    private String end_time;
    private String[] vehicles;
    private String[] vehicle_names;
    private int[] cities;
    private String[] cities_times;
    private int[] moneys;

    public Plan() {
        start_time = null;
        end_time = null;
        vehicles = null;
        cities = null;
        cities_times = null;
        moneys = null;
        vehicle_names=null;
    }

    public Plan(String start, String end, String[] ve, int[] cit,String[] time,int[] mon,String[] v_n) {
        start_time = start;
        end_time = end;
        vehicles = ve;
        cities = cit;
        cities_times = time;
        moneys = mon;
        vehicle_names = v_n;
    }
}