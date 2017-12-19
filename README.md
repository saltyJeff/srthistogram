# srthistogram
Task for Google Code In 2017-2018. Creates a histogram of the number of numbers against
minutes.
# Usage
The packaged jar is in `srthistogram_jar/srthistogram.jar`

From the command line run:

`java -jar srthistogram.jar FILE`

Where file is the .srt file to create a histogram from.

Sample output:
```
INPUT FILE: C:\example.srt
Minute 0    1   +
Minute 1    3   +++
Minute 2    0   
Minute 3    4   ++++
NUMBER OF SUBTITLES: 8
```