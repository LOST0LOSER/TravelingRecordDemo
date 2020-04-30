import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class travelServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static String input_json;
	Gson gson = new Gson();
	GetJSON data = new GetJSON();
	ResultJSON result_data = new ResultJSON();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		try{
			System.out.println("���ڴ���Post����");
			//�������Ϊutf-8,��������Ϊjson
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			/**
			 * ����json
			 */
			BufferedReader reader = request.getReader();
			input_json = reader.readLine();
			reader.close();
			System.out.println("�ѽ�������");
			//����ת��,��utf-8Ϊ��׼
			byte[] input_json_bytes=  input_json.getBytes("ISO-8859-1");//�ֽ���ISO-8859-1
			input_json = new String(input_json_bytes,"utf-8");//ת��Ϊutf-8��׼


			//��json�ַ���תΪjava����
			data = gson.fromJson(input_json, GetJSON.class);
			data.testdata();
			//System.out.println(data.start_time);


			//ҵ���߼�������
			//System.out.println(data.get_start_position());
			//����1
			int cnum = 10;
            Edge timeList[] = new Edge[5000];
            int i = test.readFile(timeList);
            dijkstra djs_plan1 = new dijkstra(i,cnum, timeList);
            bfs_dp bfs_plan2 = new bfs_dp(i,cnum, timeList);
            bfs_dp bfs_plan3 = new bfs_dp(i, cnum, timeList);
			//����1
            djs_plan1.method(data.get_start_time(), data.get_start_position(),
					data.get_destination());
            //����2
			bfs_plan2.method(data.get_start_time(), data.get_start_position(),
					data.get_destination(), "75:00");
			//����3
            bfs_plan3.method(data.get_start_time(), data.get_start_position(),
                    data.get_destination(), data.get_limited_time());

            System.out.println("test ok");

			result_data = new ResultJSON();
			//����1
			int[] cit = djs_plan1.get_cities();
			String[] vehicles = djs_plan1.get_vehicles();
			String[] vehicle_names = djs_plan1.get_vehicle_names();
			String[] time =djs_plan1.get_cities_time();
			int[] moneys = djs_plan1.get_money();
			result_data.set_plan(0,djs_plan1.get_start_time(),djs_plan1.get_end_time(),
					vehicles,cit,time,moneys,vehicle_names);
			//����2
			cit = bfs_plan2.get_cities();
			vehicles = bfs_plan2.get_vehicles();
			vehicle_names = bfs_plan2.get_vehicle_names();
			time =bfs_plan2.get_cities_time();
			moneys = bfs_plan2.get_moneys();
			result_data.set_plan(1,bfs_plan2.get_start_time(),bfs_plan2.get_end_time(),
					vehicles,cit,time,moneys,vehicle_names);
			//����3 bfs
			cit = bfs_plan3.get_cities();
			vehicles = bfs_plan3.get_vehicles();
			vehicle_names = bfs_plan3.get_vehicle_names();
			time =bfs_plan3.get_cities_time();
			moneys = bfs_plan3.get_moneys();
			result_data.set_plan(2, bfs_plan3.get_start_time(), bfs_plan3.get_end_time(),vehicles
					,cit,time,moneys,vehicle_names);
			bfs_plan3.printSolution();
            data=null;
			//��ٷ������ݣ����ڲ���?
			//����������׼

			String result_json = gson.toJson(result_data);
			result_data=null;

			/**
			 * ����json
			**/
			System.out.println(result_json);
			PrintWriter out = response.getWriter();//�ַ����������?
			//ServletOutputStream out = response.getOutputStream();//�����?
			out.write(result_json);//�����ַ�����ǰ��			
			out.flush();
			out.close();
			System.out.println("����Post���?,�ѷ�������");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
