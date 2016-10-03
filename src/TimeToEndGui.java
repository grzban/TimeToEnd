import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class TimeToEndGui extends JFrame{

	private static final long serialVersionUID = 1L;
	
	TimeToEndThread timeToTheEndThread;
	CurrentTimeThread currentTimeThread;
	
	JLabel labelcurrentTime;
	
	JLabel timeToEndLabel;
	JLabel percentFromBeginLabel;
	JLabel timeFromBeginLabel;

	JProgressBar progressBar;
	
	int dayI;
	int monthI;
	int hourI;
	int minuteI;
	int lenghtOfDutyI;
		
	Choice dayChoice;
	Choice monthChoice;
	Choice hourChoice;
	Choice minuteChoice;
	Choice lengthOfDutyChoice;
	
	public TimeToEndGui() {
		dayChoice = daysOfMonthChoice();
		monthChoice = monthsChoice();
		hourChoice = hoursChoice();
		minuteChoice = minutesChoice();
		lengthOfDutyChoice = new Choice();
		lengthOfDutyChoice.add(String.valueOf(12));
		lengthOfDutyChoice.add(String.valueOf(24));
				
		currentTimeThread = new CurrentTimeThread();
		
		
		labelcurrentTime = currentTimeThread.getCurrentTimeLabel();
		labelcurrentTime.setHorizontalAlignment(JLabel.CENTER);
		
		timeToEndLabel = new JLabel();		
		timeFromBeginLabel = new JLabel();
		percentFromBeginLabel = new JLabel();
		
		
		progressBar = new JProgressBar(0, 100);
		
		setPreferredSize(new Dimension(650, 240));
		setTitle("Odliczacz czasu");
				
		add(labelcurrentTime, BorderLayout.NORTH);
		add(stworzPanelCenter(),BorderLayout.CENTER);
		add(stworzPanelEast(), BorderLayout.EAST);
		add(stworzPanelPrzyciskow(),BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JPanel stworzPanelEast(){
		JPanel panel = new JPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setLayout(new BorderLayout());
		panel.add(stworzPanelDanych(), BorderLayout.CENTER);
		panel.add(stworzPanelPostepu(), BorderLayout.SOUTH);
		return panel;
	}
	
	public JPanel stworzPanelCenter(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		JTextArea informacja = new JTextArea("Podaj dat� oraz godz.\nzako�czenia dy�uru",2,2);
		informacja.setEditable(false);
		informacja.setEnabled(false);
		panel.add(informacja, BorderLayout.NORTH);
		panel.add(stworzPanelUstawKoniecDyzuru(),BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel stworzPanelUstawKoniecDyzuru(){
		
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(5,2,0,0));

		JLabel dzienLabel = new JLabel("DZIE�");
		JLabel miesiacLabel = new JLabel("MIESIAC");
		JLabel godzLabel = new JLabel("GODZ.");
		JLabel minLabel = new JLabel("MINUTY");
		JLabel lengthOfDutyLabel = new JLabel("DY�UR");
		
		dzienLabel.setHorizontalAlignment(JLabel.LEFT);
		miesiacLabel.setHorizontalAlignment(JLabel.LEFT);
		godzLabel.setHorizontalAlignment(JLabel.LEFT);
		minLabel.setHorizontalAlignment(JLabel.LEFT);
		lengthOfDutyLabel.setHorizontalAlignment(JLabel.LEFT);
		
		panel.add(dzienLabel);
		panel.add(dayChoice);
		panel.add(miesiacLabel);
		panel.add(monthChoice);
		panel.add(godzLabel);
		panel.add(hourChoice);
		panel.add(minLabel);
		panel.add(minuteChoice);
		
		panel.add(lengthOfDutyLabel);
		panel.add(lengthOfDutyChoice);
		
		return panel;
	}
	
	public JPanel stworzPanelPrzyciskow(){
		
		JPanel panel = new JPanel();
		
		JButton przyciskStart = new JButton("Start");
		JButton przyciskWstrzymaj = new JButton("Wstrzymaj");
		JButton przyciskStop = new JButton("Zako�cz");
		przyciskWstrzymaj.setEnabled(false);
		
		przyciskStart.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
							
				timeToTheEndThread = new TimeToEndThread();
				dayI = Integer.parseInt(dayChoice.getSelectedItem());
				monthI = monthChoice.getSelectedIndex();
				hourI = Integer.parseInt(hourChoice.getSelectedItem());
				minuteI = Integer.parseInt(minuteChoice.getSelectedItem());
				lenghtOfDutyI = Integer.parseInt(lengthOfDutyChoice.getSelectedItem());
				
				timeToTheEndThread.setDay(dayI);
				timeToTheEndThread.setMonth(monthI);
				timeToTheEndThread.setHour(hourI);
				timeToTheEndThread.setMinute(minuteI);
				timeToTheEndThread.setLenghtOfDuty(lenghtOfDutyI);
				
				timeToTheEndThread.setTimeToEndLabel(timeToEndLabel);
				timeToTheEndThread.setPercentFromBeginWynikLabel(percentFromBeginLabel);
				timeToTheEndThread.setTimeFromBeginLabel(timeFromBeginLabel);
				timeToTheEndThread.setProgressBar(progressBar);
				//timeToTheEndThread.setWatekRunFlaga(true);
						
				timeToTheEndThread.start();
				
				przyciskStart.setEnabled(false);
				przyciskWstrzymaj.setEnabled(true);
				dayChoice.setEnabled(false);
				monthChoice.setEnabled(false);
				hourChoice.setEnabled(false);
				minuteChoice.setEnabled(false);
				lengthOfDutyChoice.setEnabled(false);
			}
		});
		
		przyciskWstrzymaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				przyciskStart.setEnabled(true);
				dayChoice.setEnabled(true);
				monthChoice.setEnabled(true);
				hourChoice.setEnabled(true);
				minuteChoice.setEnabled(true);
				lengthOfDutyChoice.setEnabled(true);
				timeToTheEndThread.setWatekRunFlaga(false);
			}
		});
		przyciskStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		panel.add(przyciskStart);
		panel.add(przyciskWstrzymaj);
		panel.add(przyciskStop);
		
		return panel;
	}

	public JPanel stworzPanelDanych(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel doKoncaDyzuruPozostaloLabel = new JLabel("Doko�ca dy�uru pozosta�o: ");
		JLabel mineloLabel = new JLabel("Min�o: ");
		JLabel odRozpoczeciaDyzuruMineloLabel = new JLabel("Od rozpocz�cia dy�uru min�o: ");
		
		timeToEndLabel.setForeground(Color.RED);
	
		doKoncaDyzuruPozostaloLabel.setHorizontalAlignment(JLabel.RIGHT);
		mineloLabel.setHorizontalAlignment(JLabel.RIGHT);
		odRozpoczeciaDyzuruMineloLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		panel.add(doKoncaDyzuruPozostaloLabel);
		panel.add(timeToEndLabel);
		panel.add(mineloLabel);
		panel.add(timeFromBeginLabel);
		panel.add(odRozpoczeciaDyzuruMineloLabel);
		panel.add(percentFromBeginLabel);
		
		return panel;
	}
	
	public JPanel stworzPanelPostepu(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(1, 1));
		progressBar.setValue(0);
	    progressBar.setStringPainted(true);
	    
	    panel.add(progressBar);
	    return panel;
	}
	
	public Choice daysOfMonthChoice(){
		Choice day = new Choice();
		
		for (int i = 1; i <=31; i++){
			day.add(String.valueOf(i));
		}
		
		return day;
	}
	
	public Choice monthsChoice(){
		Choice month = new Choice();
		
		String [] months ={"Stycze�", "Luty", "Marzec", "Kwiecie�", "Maj", "Czerwiec", "Lipiec", "Sierpie�", "Wrzesie�", 
				"Pa�dziernik", "Listopad", "Grudzie�"};
		
		for (int i = 0; i <= 11; i++){
			month.add(months[i]);
		}
		
		return month;
	}
	
	public Choice hoursChoice(){
		Choice hour = new Choice();

		for (int i = 0; i <=23; i++){
			hour.add(String.valueOf(i));
		}
		
		return hour;
	}
	
	public Choice minutesChoice(){
		Choice minute = new Choice();
		
		for (int i = 0; i <=59; i++){
			minute.add(String.valueOf(i));
		}
		return minute;
	}
	
	public static void main(String[] args) {
		new TimeToEndGui();
	}
}
