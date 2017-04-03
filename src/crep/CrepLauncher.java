package crep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class CrepLauncher {

    @Argument(required = true, metaVar = "classCrep")
    private String classCrep;

    @Option(name = "-r")
    private boolean r;

    @Option(name = "-v")
    private boolean v;

    @Option(name = "-i")
    private boolean i;

    @Argument(required = true, metaVar = "word", index = 1)
    private String inputWord;

    @Argument(required = true, metaVar = "fileName", index = 2)
    private String inputFileName;

    public static void main(String[] args) throws IOException {
        new CrepLauncher().launch(args);
    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.print("crep [-v] [-i] [-r] word inputname.txt");
        }

        Crep crep = new Crep(inputWord, inputFileName);
        System.out.println(crep.creper(r, v, i));
    }
}