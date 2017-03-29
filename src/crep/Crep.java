package crep;

import java.io.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Crep {

    private final String word;
    private final String fileName;

    public Crep(String word, String fileName) {
        this.word = word;
        this.fileName = fileName;
    }

/*
-r (regex) вместо слова задаёт регулярное выражение для поиска(на консоль выводятся только строки, содержащие данное выражение)
-v инвертирует условие фильтрации (выводится только то, что ему НЕ соответствует)
-i игнорировать регистр слов
*/

    public String creper(Function<String, Boolean> lambda) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            StringBuilder str = new StringBuilder();
            while (line != null) {
                if (lambda.apply("-r")) {
                    if (Pattern.compile(word).matcher(line).find()) str.append(line).append("\n");
                }
                if (lambda.apply("-v")) {
                    if (!line.contains(word)) str.append(line).append("\n");
                }
                if (lambda.apply("-i")) {
                    if (line.toLowerCase().contains(word.toLowerCase())) str.append(line).append("\n");
                }
                line = br.readLine();
            }
            return str.deleteCharAt(str.lastIndexOf("\n")).toString();
        }
    }
}