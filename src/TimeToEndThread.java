import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;


public class TimeToEndThread extends Thread {
	
	private TimeToEnd timeToTheEnd;
	
	private JLabel timeToEndLabel;
	private JLabel percentFromBeginlabelWynikLabel;
	private JLabel timeFromBeginLabel;
	
	private JProgressBar progressBar;
	private CurrentTime currentTime;
	
	private int postep;
	private JOptionPane optionPane;
	private boolean watekRunFlaga ;
	
	int day;
	int month;
	int hour;
	int minute;
	int lenghtOfDuty;
		
	public TimeToEndThread(){
		
		day = getDay();
		month = getMonth();
		hour = getHour();
		minute = getMinute();
		lenghtOfDuty = getLenghtOfDuty();
		
		timeToEndLabel = new JLabel();
		progressBar = new JProgressBar();
	}
	
	@Override
	public void run() {
		
		timeToTheEnd = new TimeToEnd(day, month, hour, minute, lenghtOfDuty);
		
		if(timeToTheEnd.diferenceEndTimeBeginTmeInMilisec() > 0){
			setWatekRunFlaga(true);
		}
		else if(timeToTheEnd.diferenceEndTimeBeginTmeInMilisec() == 0){
			JOptionPane.showMessageDialog(optionPane, "Jest: " + currentTime.showCurrentTime() +"\nCzas do domu!!!");
			setWatekRunFlaga(false);
		}
		else if(timeToTheEnd.diferenceEndTimeBeginTmeInMilisec() < 0){
			JOptionPane.showMessageDialog(optionPane, "Nie licz� czasu w przesz�o��");
			setWatekRunFlaga(false);
		}
		else if(timeToTheEnd.endMaximalTime() - timeToTheEnd.endTimeInMilisec() < 0){
			JOptionPane.showMessageDialog(optionPane, "Hola , hola pi�kny kawalerze!\nZa daleko w przysz�o��");
			
			System.out.println("Max: "  + timeToTheEnd.endMaximalTime() + "\nEnd time: " + timeToTheEnd.endTimeInMilisec());
			setWatekRunFlaga(false);
		}
		
		
		while(timeToTheEnd.diferenceEndTimeBeginTmeInMilisec() >= 0 && isWatekRunFlaga()){
			System.out.println(timeToTheEnd.diferenceEndTimeBeginTmeInMilisec());
			timeToEndLabel.setText(timeToTheEnd.endTimeInNormalTime() + "");
			setTimeToEndLabel(timeToEndLabel);
		
			timeFromBeginLabel.setText(timeToTheEnd.timeFromBeginInNormalTime());
			setTimeFromBeginLabel(timeFromBeginLabel);
		
			percentFromBeginlabelWynikLabel.setText(timeToTheEnd.progressInPercent());
			setPercentFromBeginWynikLabel(percentFromBeginlabelWynikLabel);
									
			postep = (int)(timeToTheEnd.progressInPercentDouble());
			progressBar.setValue(postep);
			
			new Przypominacz();
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int getLenghtOfDuty() {
		return lenghtOfDuty;
	}

	public void setLenghtOfDuty(int lenghtOfDuty) {
		this.lenghtOfDuty = lenghtOfDuty;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public boolean isWatekRunFlaga() {
		return watekRunFlaga;
	}

	public void setWatekRunFlaga(boolean watekRunFlaga) {
		this.watekRunFlaga = watekRunFlaga;
	}

	public JLabel getTimeToEndLabel() {
		return timeToEndLabel;
	}

	public void setTimeToEndLabel(JLabel timeToEndLabel) {
		this.timeToEndLabel = timeToEndLabel;
	}

	public JLabel getPercentFromBeginlabelWynikLabel() {
		return percentFromBeginlabelWynikLabel;
	}

	public void setPercentFromBeginWynikLabel(JLabel percentFromBeginlabelWynikLabel) {
		this.percentFromBeginlabelWynikLabel = percentFromBeginlabelWynikLabel;
	}

	public JLabel getTimeFromBeginLabel() {
		return timeFromBeginLabel;
	}

	public void setTimeFromBeginLabel(JLabel timeFromBeginLabel) {
		this.timeFromBeginLabel = timeFromBeginLabel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public TimeToEnd getTimeToTheEnd() {
		return timeToTheEnd;
	}

	public void setTimeToTheEnd(TimeToEnd timeToTheEnd) {
		this.timeToTheEnd = timeToTheEnd;
	}

	public CurrentTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(CurrentTime currentTime) {
		this.currentTime = currentTime;
	}
	
}
