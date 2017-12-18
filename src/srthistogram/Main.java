package srthistogram;

import java.io.IOException;

import com.jonathanedgecombe.srt.InvalidTimestampFormatException;
import com.jonathanedgecombe.srt.Subtitle;
import com.jonathanedgecombe.srt.SubtitleFile;
import com.jonathanedgecombe.srt.Timestamp;

import picocli.CommandLine;
import picocli.CommandLine.MissingParameterException;

public class Main {

	public static void main(String[] args) throws InvalidTimestampFormatException, IOException {
		try {
			CmdOpts options = CommandLine.populateCommand(new CmdOpts(), args);
			if(options.helpRequested) {
				CommandLine.usage(options, System.out);
				return;
			}
			System.out.println("Reading from: "+options.file.getAbsolutePath());
			SubtitleFile exampleFile = new SubtitleFile(options.file);
			int currentMinutes = 0;
			int currentCount = 0;
			for(Subtitle s : exampleFile.getSubtitles()) {
				if(within1Minute(currentMinutes, s.getStartTime())) {
					currentCount++;
				}
				else {
					while(!within1Minute(currentMinutes, s.getStartTime())) { //fwd through empty minutes
						printHistogram(currentMinutes, currentCount);
						currentMinutes++;
						currentCount = 0;
					}
					currentCount = 1;
				}
			}
		}
		catch(MissingParameterException e) {
			System.err.println("No SRT file was given, switch on -h for help");
		}
	}
	public static boolean within1Minute(int min, Timestamp s) {
		int timeStampMins = s.getHours() * 60 + s.getMinutes();
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
