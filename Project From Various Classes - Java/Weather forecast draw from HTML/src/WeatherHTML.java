import java.io.*;
import java.net.*;

public class WeatherHTML {

	public static void writeHTMLToFile(String strURL, String htmlFile) {
		String html = downloadHTML(strURL);

		File file = new File("80113.txt");		
		try {
			PrintWriter fileOut = new PrintWriter(file);
			fileOut.println(html);
			fileOut.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		
	}

	// This method connects this program to a URL and downloads
	// the HTML from that URL, returning it as a single string.
	public static String downloadHTML(String strURL) {
		StringBuilder str = new StringBuilder();

		try {
			URL url = new URL(strURL);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while (true) {
				String nextLine = reader.readLine();
				if (nextLine == null)
					break;
				str.append(nextLine + "\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str.toString();
	}


	// This method adds the zip code to the proper URL to
	// then use the above two methods to update the HTML
	// in the text file.
	public static void updateWeather(String zipCode) {
		try {
			if (zipCode.length() == 0)
				return;
			String url = "https://weather.com/weather/today/l/" + zipCode + ":4:US";
			WeatherHTML.writeHTMLToFile(url, zipCode + ".txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
