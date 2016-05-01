import java.net.*;
import java.io.*;
import java.util.Date;
import org.json.JSONObject;

public class API {
	public static String APIkey = "faae5f60e6e77a5e188aa029fab0e6d0";		// my API key

	public static String request(URL url) throws IOException {
		URLConnection yc = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		StringBuilder sb = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}

		in.close();

		return sb.toString();
	}

	public static String getHistory (String username, Date from, Date to) throws IOException {
		URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=" + 
							username + "&from=" + from.getTime() / 1000 + "&to=" + to.getTime() / 1000 +
							"&api_key=" + APIkey + "&format=json");
		return request(url);
	}

	public static String getHistory (String username) throws IOException {
		return getHistory(username, new Date(0), new Date());
	}

    public static void main(String[] args) throws IOException {
        String response = getHistory("dev9789", new Date(2016 - 1900, 4, 1, 20, 30), new Date());
        
        JSONObject json = new JSONObject(response);
        System.out.println(json.toString(2));
    }
}