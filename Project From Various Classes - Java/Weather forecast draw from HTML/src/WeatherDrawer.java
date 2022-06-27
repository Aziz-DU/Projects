import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

public class WeatherDrawer {

	private String selectedZipCode;
	private WeatherLogo[] logos;
	private int canvasHeight=400;
	private int canvasWidth=1000;
	public WeatherDrawer(String zipCode) {
	selectedZipCode = zipCode;
	WeatherHTML.updateWeather(selectedZipCode);	
	
	StdDraw.setCanvasSize(canvasWidth,canvasHeight);
	StdDraw.setXscale(0, canvasWidth);
	StdDraw.setYscale(0, canvasHeight);
	
	logos = new WeatherLogo[5];
	fillLogoArray();
	draw();
	}
	
	private void fillLogoArray() {
		String html = "";

		File selectedZipCodeFile = new File("80113.txt");
		try {
			Scanner fileIn = new Scanner(selectedZipCodeFile);
			while(fileIn.hasNextLine()) {
				String strz=fileIn.nextLine();
				html=html+strz;
			}
			fileIn.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		
		
		// These variables will be used to parse the
		// html string and access each temperature,
		// day, and weather one by one
		int tempIndex = -1;
		String temp = "";
		int dayIndex = -1;
		String day = "";
		int weatherIndex = -1;
		String weather = "";

		for (int i = 0; i < this.logos.length; i++) {
			// You are encouraged to read through it to see how it works though.
			// Temperature
			tempIndex = html.indexOf("class=\"today-daypart-temp\"><span class=\"\">", tempIndex + 1);
			temp = html.substring(tempIndex + 42, html.indexOf("<sup>", tempIndex));

			// Day
			dayIndex = html.indexOf("daypartName\">", dayIndex + 1);
			day = html.substring(dayIndex + 13, html.indexOf("</span>", dayIndex));

			// Weather
			weatherIndex = html.indexOf("-phrase\">", weatherIndex + 1);
			if (html.indexOf("</span>", weatherIndex) < html.indexOf("</div>", weatherIndex)) {
				weather = html.substring(weatherIndex + 9, html.indexOf("</span>", weatherIndex));
			} else {
				weather = html.substring(weatherIndex + 9, html.indexOf("</div>", weatherIndex));
			}
			weather = weather.toLowerCase();
			
			if(weather.contains("/")) {				//while testing, had the weather of some cities go over 20 charecters which made it look ugly so added this just in case to clean it up
				int slashPos = weather.indexOf("/");
				weather=weather.substring(0,slashPos);
			}
			
			int xPos=200*i;							// using this variable to control the position of the logos					
			
			if(weather.contains("rain")) {
				RainyLogo rl = new RainyLogo(xPos+100,300.0,temp,day,weather);
				this.logos[i]=rl;
			}
			else if(weather.contains("cloud")&&(!weather.contains("partly"))) {
				CloudyLogo cl = new CloudyLogo(xPos+100,300.0,temp,day,weather);
				this.logos[i]=cl;
			}
			else if(weather.contains("shower")) {
				ShowersLogo sl = new ShowersLogo(xPos+100,300.0,temp,day,weather);
				this.logos[i]=sl;
			}
			else if(weather.contains("partly cloudy")) {
				PartlyCloudyLogo pcl = new PartlyCloudyLogo(xPos+100,300.0,temp,day,weather);
				this.logos[i]=pcl;
			}
			else {
				SunnyLogo sl = new SunnyLogo(xPos+100,315,temp,day,weather);
				this.logos[i]=sl;	
				}
			
		}
	}

	public void draw() {
		// temperature, day, and weather text along
		// with the logo itself.
		for(int i = 0; i < this.logos.length; i++) { //creating a loop for controling the elements indivisually
			int xPos=200*i;							// using this variable to control the position 

	    	if(i==0) {								//changing the color of each box
				StdDraw.setPenColor(102,169,174);

		    }
			if(i==1) {
				StdDraw.setPenColor(63,98,101);

			}
			else if(i==2) {
				StdDraw.setPenColor(102,169,174);

			}
			else if(i==3) {
				StdDraw.setPenColor(63,98,101);

			}
			else if(i==4) {
				StdDraw.setPenColor(102,169,174);

			}
			
			StdDraw.filledRectangle(xPos+100,200,100,200);
			this.logos[i].draw();
			StdDraw.setPenColor(32,32,32);
		    StdDraw.setFont(new Font("msyh", Font.BOLD, 30));
			StdDraw.text(xPos+100, 200,this.logos[i].day );
		    StdDraw.setFont(new Font("msyh", Font.BOLD, 70));
			StdDraw.setPenColor(232, 232, 232);
			StdDraw.text(xPos+100, 120,this.logos[i].temperature );
		    StdDraw.setFont(new Font("msyh", Font.BOLD, 20));
			StdDraw.setPenColor(32,32,32);
			StdDraw.text(xPos+100, 50,this.logos[i].weatherType );
		}
	}
}
