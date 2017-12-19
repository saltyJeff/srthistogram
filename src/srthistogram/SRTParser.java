package srthistogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class SRTParser extends LinkedList<Subtitle> {
	private Scanner input;
	public SRTParser (File f) throws FileNotFoundException {
		input = new Scanner(f);
		while(input.hasNextLine()) {
			String maybeBOM = input.nextLine();
			if(maybeBOM.charAt(0) == 65279) {
				maybeBOM = maybeBOM.substring(1);
			}
			int index = Integer.parseInt(maybeBOM);
			String duration = input.nextLine();
			String[] times = duration.split(" --> ");
			Timestamp start = new Timestamp(times[0]);
			Timestamp end = new Timestamp(times[1]);
			StringBuilder text = new StringBuilder();
			while(input.hasNextLine()) {
				String s = input.nextLine();
				if(!s.equals("")) {
					text.append(s);
					text.append('\n');
				}
				else {
					break;
				}
			}
			Subtitle sub = new Subtitle(index, start, end, text.toString());
			this.addLast(sub);
		}
	}
}
