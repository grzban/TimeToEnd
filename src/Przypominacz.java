import javax.swing.JOptionPane;

public class Przypominacz {
	
	private JOptionPane optionPane;
		
	private CurrentTime currentTime;
	
	public Przypominacz() {
		
		currentTime = new CurrentTime();
		
		przypomnijWeryfikacjePZ();
		przypomnijPosilek();
		przypomnijWeryfikacjaPZRaport();
	}
	
	private void przypomnijWeryfikacjaPZRaport() {
		int[] godziny ={18,6};
		
		for (int i = 0; i< godziny.length; i++){
			if (currentTime.showCurrentMinute() == 40  && currentTime.showCurrentSecond() == 00 && currentTime.showCurrentHourOfDay() == godziny[i]){				
				showMessage("Wy�lij raport Weryfikacja PZ");
			}
		}
	}

	private void przypomnijPosilek() {
		
		int[] godziny ={8,11,14,17,20,23};
				
		for (int i = 0; i< godziny.length; i++){
			
			if (currentTime.showCurrentMinute() == 40  && currentTime.showCurrentSecond() == 00 && currentTime.showCurrentHourOfDay() == godziny[i]){				
				showMessage("Czas na posi�ek");
			}
		}
	}

	public void przypomnijWeryfikacjePZ(){
				
		int[] godziny ={0,2,4,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
		int[] sec = {0,15,30,45};
				
		for (int i = 0; i< godziny.length; i++){
			for(int k = 0; k<sec.length; k++){
					
				if (currentTime.showCurrentMinute() == 30  && currentTime.showCurrentSecond() == sec[k] && currentTime.showCurrentHourOfDay() == godziny[i]){
					showMessage("Czas na weryfikacj� PZ");
				}
			}
		}
	}
	
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(optionPane, "Jest: " + currentTime.showCurrentTime() + "\n" + message);
	}
}
