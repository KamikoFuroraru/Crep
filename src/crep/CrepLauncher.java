package crep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class CrepLauncher {

    @Option(name = "-r")
    private boolean r;

    @Option(name = "-v")
    private boolean v;

    @Option(name = "-i")
    private boolean i;

    @Argument(required = true, metaVar = "word")
    private String inputWord;

    @Argument(required = true, metaVar = "fileName", index = 1)
    private String inputFileName;

    public static void main(String[] args) {
        new CrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.print("crep [-v] [-i] [-r] word inputname.txt");
            return;
        }

        Crep crep = new Crep(inputWord, inputFileName);
        try {
            if (r && i || r && v || i && v) System.err.println("Unacceptable combination");
            else {
                if (r) System.out.println(crep.r());
                if (v) System.out.println(crep.v());
                if (i) System.out.println(crep.i());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}