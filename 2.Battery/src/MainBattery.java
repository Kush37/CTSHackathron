import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainBattery {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader f = new FileReader("D:\\Battery.txt");
		BufferedReader br = new BufferedReader(f);
		JSONObject fj = new JSONObject();

		String text;

		while ((text = br.readLine()) != null) {

			if (text.contains("Uid u0a202")) {
				String st[] = text.split("\\s+");
				float battery = Float.parseFloat(st[3]);

				float btpcnt = battery / 1000;
				float d = Float.parseFloat(String.format("%.3f", btpcnt));

				fj.put("Battery_percentage", d);
				fj.put("Battery_drain", battery);
			} else if (text.contains("Foreground")) {
				String st[] = text.split("\\s+");
				String ft = st[3] + " " + st[4] + " " + st[5] + " " + st[6] + " " + st[7] + " " + st[8];

				fj.put("Foreground_time", ft);

			}

		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(fj);
		System.out.println(json);

		FileWriter file = new FileWriter("D:\\output2.json");
		file.write(fj.toJSONString());
		file.close();
	}

}
