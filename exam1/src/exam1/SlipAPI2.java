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

			// if부터 작성

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			// MAP구조로
			// gson 이용 map에 저장하기(1줄로 가능)
			Gson gson = new Gson();
			map = gson.fromJson(result, Map.class);

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
	// end of insertSlip
}
