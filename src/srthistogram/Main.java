package srthistogram;

import picocli.CommandLine;
import picocli.CommandLine.MissingParameterException;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			CmdOpts options = CommandLine.populateCommand(new CmdOpts(), args);
			if(options.helpRequested) {
				CommandLine.usage(options, System.out);
				return;
			}
			System.out.println("INPUT FILE: "+options.file.getAbsoluteFile().toString());
			SRTParser parser = new SRTParser(options.file);
			int currentMinutes = 0;
			int currentCount = 0;
			for(Subtitle s : parser) {
				if(within1Minute(currentMinutes, s.startTime)) {
					currentCount++;
				}
				else {
					while(!within1Minute(currentMinutes, s.startTime)) { //fwd through empty minutes
						printHistogram(currentMinutes, currentCount);
						currentMinutes++;
						currentCount = 0;
					}
					currentCount = 1;
				}
			}
			printHistogram(currentMinutes, currentCount);
			System.out.println("NUMBER OF SUBTITLES: "+parser.size());
		}
		catch(MissingParameterException e) {
			System.err.println("No SRT file was given, switch on -h for help");
		}
	}
	public static boolean within1Minute(int min, Timestamp s) {
		int timeStampMins = s.hours * 60 + s.minutes;
		return min == timeStampMins;
	}
	public static void printHistogram(int min, int count) {
		System.out.format("Minute %d\t%d\t", min, count);
		for(int i = 0; i < count; i++) {
			System.out.print('+');
		}
		System.out.println();
	}
}
