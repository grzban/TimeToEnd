import java.text.DecimalFormat;
import java.util.Calendar;

public class TimeToEnd {

	private int dayOfMonth;
	private int month;
	private int hourOfDay;
	private int minute;
	private int secound;
	
	private int lengthOfDuty;
	long leftTime;
	private long leftHours;
	private long leftMinutes;
	private long leftSecunds;
	
	public TimeToEnd(int dayOfMonth, int month, int hourOfDay, int minute, int lenghthOfDuty) {
		this.dayOfMonth = dayOfMonth;
		this.month = month;
		this.hourOfDay = hourOfDay;
		this.minute = minute;
		this.lengthOfDuty = lenghthOfDuty;
	}
		
	private long convertEndTimeToMilisec(){
		Calendar endTime = Calendar.getInstance();
		
		endTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		endTime.set(Calendar.MONTH, month);
		endTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
		endTime.set(Calendar.MINUTE, minute);
		endTime.set(Calendar.SECOND, secound);
		
		long result = endTime.getTimeInMillis();
		
		return result;
	}
		
	private long getCurrentTimeInMilisec(){
		Calendar currentTime = Calendar.getInstance();
		
		long result = currentTime.getTimeInMillis();
		return result;
	}
	
	public long endTimeInMilisec(){
		return convertEndTimeToMilisec();
	}
	
	public long diferenceEndTimeBeginTmeInMilisec(){
		long result = endTimeInMilisec() - getCurrentTimeInMilisec();
		return result;
	}
	
	public String endTimeInNormalTime(){
		
		leftTime = diferenceEndTimeBeginTmeInMilisec();
		
		leftHours = leftTime/1000/60/60;
		leftMinutes = leftTime/1000/60 - leftHours * 60;
		leftSecunds = leftTime/1000 - leftMinutes*60 - leftHours*60*60;
		
		String leftHoursString = leftHours + " h";
		String leftMinutesString = leftMinutes + " m";
		String leftSecundsString = leftSecunds + " s";
		
		if(leftHours < 10){
			leftHoursString = "0" + leftHoursString;
		}
		if(leftMinutes <10){
			leftMinutesString = "0" + leftMinutesString;
		}
		if(leftSecunds < 10){
			leftSecundsString = "0" + leftSecundsString;
		}
		
		String result =  leftHoursString + " " + leftMinutesString + " " + leftSecundsString ;
		
		return result;
	}
	
	public double progressInPercentDouble(){
		
		double leftTimeInSecounds = leftTime/1000;
		double lenghtOfDutyInSecunds = lengthOfDuty*60*60;
		
		double leftTimeInPercent = (1 - leftTimeInSecounds/lenghtOfDutyInSecunds) * 100;
		
		return leftTimeInPercent;
	}
	
	public String progressInPercent(){
		
		//double leftTimeInSecounds = leftTime/1000;
		//double lenghtOfDutyInSecunds = lengthOfDuty*60*60;
		
		//double leftTimeInPercent = (1 - leftTimeInSecounds/lenghtOfDutyInSecunds) * 100;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		String result = df.format(progressInPercentDouble()) + " %";
		
		return result;
	}
	
	public String timeFromBeginInNormalTime(){
		
		double leftTimeInSecounds = leftTime/1000;
		double lenghtOfDutyInSecunds = lengthOfDuty*60*60;
	
		double pastTimeOfDutyInSeconds = lenghtOfDutyInSecunds - leftTimeInSecounds;
		
		int pastHours = (int) pastTimeOfDutyInSeconds/60/60;
		int pastMinutes =(int) (pastTimeOfDutyInSeconds/60 - pastHours * 60);
		int pastSecunds = (int)(pastTimeOfDutyInSeconds - pastMinutes*60 - pastHours*60*60);
		
		String pastHoursString = pastHours + " h";
		String pastMinutesString = pastMinutes + " m";
		String pastSecundsString = pastSecunds + " s";
		
		if(pastHours < 10){
			pastHoursString = "0" + pastHoursString;
		}
		if(pastMinutes <10){
			pastMinutesString = "0" + pastMinutesString;
		}
		if(pastSecunds < 10){
			pastSecundsString = "0" + pastSecundsString;
		}
		
		String result = pastHoursString + " " + pastMinutesString + " " + pastSecundsString;
		
		return result;
	}
	
	public long lengthOfDutyInMiliSec(){
		long result = getLengthOfDuty() * 60*60*1000;
		return result;
	}

	public long endMaximalTime(){
		long result = lengthOfDutyInMiliSec() + getCurrentTimeInMilisec();
		return result;
	}
	
	public int getLengthOfDuty() {
		return lengthOfDuty;
	}

	public void setLengthOfDuty(int lengthOfDuty) {
		this.lengthOfDuty = lengthOfDuty;
	}
}
