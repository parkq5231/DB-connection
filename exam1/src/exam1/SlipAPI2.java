package exam1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.OutputBuffer;

import com.google.gson.Gson;

public class SlipAPI2 {
	// BANK 로그인
	public Map<String, Object> insertSlip(String param) {
		// URL 바꾸기
		String reqURL = "http://192.168.0.83/exam/insertSlip";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청+ 파라미터 설정 + 연결채크
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setDoOutput(true);

			// 연결 후 입력값 가져옴
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
			writer.write(param);
			// buffer클래스 사용하여 값 전달
			writer.flush();
			writer.close();
			os.close();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			StringBuilder sb = new StringBuilder();
			//연결 성공일 때
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				//값을 읽어내어 추가함
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = "";

				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println("token error " + conn.getResponseMessage());
			}
			// json
			Gson gson = new Gson();
			map = gson.fromJson(sb.toString(), Map.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
	// end of insertSlip
}
