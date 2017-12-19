package srthistogram;

public class Timestamp {
	public int hours;
	public int minutes;
	public int seconds;
	public int millies;
	public Timestamp () {
		hours = minutes = seconds = millies = 0;
	}
	public Timestamp(String s) {
		String[] hms = s.split(":");
		hours = Integer.parseInt(hms[0]);
		minutes = Integer.parseInt(hms[1]);
		String[] sms = hms[2].split(",");
		seconds = Integer.parseInt(sms[0]);
		millies = Integer.parseInt(sms[1]);
	}
	@Override
	public String toString () {
		return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, millies);
	}
}
