import javax.swing.JLabel;

public class CurrentTimeThread extends Thread {
	CurrentTime currentTime;
	JLabel currentTimeLabel;
	
	public CurrentTimeThread() {
		currentTimeLabel = new JLabel();
		start();
	}
	
	@Override
	public void run() {
		while(true){
			currentTime = new CurrentTime();
			
			currentTimeLabel.setText(currentTime.showCurrentTime() + "      " + currentTime.showCurrentDate());
			setCurrentTimeLabel(currentTimeLabel);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public JLabel getCurrentTimeLabel() {
		return currentTimeLabel;
	}

	public void setCurrentTimeLabel(JLabel currentTimeLabel) {
		this.currentTimeLabel = currentTimeLabel;
	}

	public CurrentTime getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(CurrentTime currentTime) {
		this.currentTime = currentTime;
	}
}
