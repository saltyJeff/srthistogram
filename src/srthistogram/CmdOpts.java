package srthistogram;

import java.io.File;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class CmdOpts {
	@Option(names = { "-h", "--help" }, usageHelp = true, description = "Help")
    public boolean helpRequested = false;
	@Parameters(arity="1", paramLabel="FILE", description = "the subtitle file")
	public File file;
}
