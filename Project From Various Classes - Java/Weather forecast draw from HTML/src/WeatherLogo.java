public abstract class WeatherLogo {
	protected double xcord,ycord;
	protected String temperature, day, weatherType;
	
    public WeatherLogo(double x,double y, String temp,String _day, String weatherT) {
		xcord =x;
		ycord=y;
		temperature=temp;
		day=_day;
		weatherType=weatherT;
    }
    public abstract void draw();
}
