import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class MainMemory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader f = new FileReader("D:\\Memory.txt");
			BufferedReader br = new BufferedReader(f);

			int i = 0, k = 0;

			float max = 0;
			float sum = 0;
			String text;
			JSONObject values = new JSONObject();
			JSONObject fj = new JSONObject();
			while ((text = br.readLine()) != null) {
				if (i % 2 != 0) { // even lines
					k = k + 1;
					text = text.replaceAll("( )+", " ");
					String sp[] = text.split("\\s");
					// System.out.println(sp[2]+" "+sp[0]+" "+sp[1]);
					String sec = k + "s";
					float val = Float.parseFloat(sp[2]) / (1024);
					sum = sum + val;
					if (val > max) {
						max = val;
					}
					// System.out.println(String.format("%.2f", val));
					values.put(sec, Float.parseFloat(String.format("%.2f", val)));

				}

				i++;
			}

			// System.out.println(max);
			// System.out.println(sum/k);

			fj.put("AverageMemory(MB)", Float.parseFloat(String.format("%.2f", sum / k)));
			fj.put("values", values);
			fj.put("MaxMemory(MB)", Float.parseFloat((String.format("%.2f", max))));
			fj.put("Usecasename", "Homepage");

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(fj);
			System.out.println(json);

			JSONArray fa = new JSONArray();
			fa.add(fj);
			FileWriter file = new FileWriter("D:\\output1.json");
			file.write(fa.toJSONString());
			file.close();
			f.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
