package crep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.function.Function;

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

        Function<String, Boolean> fun1 = (String s) -> s.equals("-r");
        Function<String, Boolean> fun2 = (String s) -> s.equals("-v");
        Function<String, Boolean> fun3 = (String s) -> s.equals("-i");

        try {
            if (r) System.out.println(crep.creper(fun1));
            if (v) System.out.println(crep.creper(fun2));
            if (i) System.out.println(crep.creper(fun3));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}