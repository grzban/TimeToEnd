import java.util.Calendar;

public class CurrentTime {

	private String hour;
	private String minute;
	private String second;
	private String dayOfMonth;
	private String month;
	private String year;
	private Calendar currentTime;
	
	public CurrentTime() {
		currentTime = Calendar.getInstance();
	}

	public String showCurrentTime(){
		
		hour = showCurrentHourOfDay() + "";
		minute = showCurrentMinute() + "";
		second = showCurrentSecond() + "";
		
		if(showCurrentHourOfDay() <10){
			hour = "0" + hour;
		}
		if(showCurrentMinute()<10){
			minute = "0" + minute;
		}
		if (showCurrentSecond() <10){
			second = "0" + second;
		}
		
		String result = hour + ":" + minute + ":" + second;
		return result;
	}
	
	public String showCurrentDate(){
		
		dayOfMonth = showCurrentDayOfMonth() + "";
		month = showCurrentMonth() + "";
		year = showCurrentYear() + "";
		
		if (showCurrentDayOfMonth() < 10){
			dayOfMonth = "0" + dayOfMonth;
		}
				
		String result = dayOfMonth + " " + month + " " + year;
		
		return result;
	}
	
	public int showCurrentHourOfDay(){
		int result = currentTime.get(Calendar.HOUR_OF_DAY);
		return result;
	}
	
	public int showCurrentMinute(){
		int result = currentTime.get(Calendar.MINUTE);
		return result;
	}
	
	public int showCurrentSecond(){
		int result = currentTime.get(Calendar.SECOND);
		return result;
	}
	
	public int showCurrentDayOfMonth(){
		int result = currentTime.get(Calendar.DAY_OF_MONTH);
		return result;
	}
	
	public String showCurrentMonth(){
		int  index = currentTime.get(Calendar.MONTH);
		String [] months ={"Stycze�", "Luty", "Marzec", "Kwiecie�", "Maj", "Czerwiec", "Lipiec", "Sierpie�", "Wrzesie�", 
				"Pa�dziernik", "Listopad", "Grudzie�"}; 
		
		String result = months[index];
		return result;
	}
	
	public int showCurrentYear(){
		int result = currentTime.get(Calendar.YEAR);
		return result;
	}
	
	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecund() {
		return second;
	}

	public void setSecund(String second) {
		this.second = second;
	}

	public String getDay() {
		return dayOfMonth;
	}

	public void setDay(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
