package srthistogram;

public class Subtitle {
	public int index;
	public Timestamp startTime;
	public Timestamp endTime;
	public String text;
	public Subtitle(int i, Timestamp s, Timestamp e, String t) {
		index = i;
		startTime = s;
		endTime = e;
		text = t;
	}
	@Override
	public String toString () {
		return index+"\n"+
				startTime.toString()+" --> "+endTime.toString()+"\n"+
				text;
	}
}
