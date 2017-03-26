package crep;

import java.io.*;
import java.util.regex.Pattern;

public class Crep {

    private final String word;

    private final String fileName;

    public Crep(String word, String fileName) {
        this.word = word;
        this.fileName = fileName;
    }

//-r (regex) вместо слова задаёт регулярное выражение для поиска(на консоль выводятся только строки, содержащие данное выражение)
    public String r() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            final Pattern p = Pattern.compile(word);
            StringBuilder str = new StringBuilder();
            while (line != null) {
                if (p.matcher(line).find()) str.append(line).append("\n");
                line = br.readLine();
            }
            return str.deleteCharAt(str.lastIndexOf("\n")).toString();
        }
    }

//-v инвертирует условие фильтрации (выводится только то, что ему НЕ соответствует)
    public String v() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            StringBuilder str = new StringBuilder();
            while (line != null) {
                if (!line.contains(word)) str.append(line).append("\n");
                line = br.readLine();
            }
            return str.deleteCharAt(str.lastIndexOf("\n")).toString();
        }
    }

//-i игнорировать регистр слов
    public String i() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            StringBuilder str = new StringBuilder();
            while (line != null) {
                if (line.toLowerCase().contains(word.toLowerCase())) str.append(line).append("\n");
                line = br.readLine();
            }
            return str.deleteCharAt(str.lastIndexOf("\n")).toString();
        }
    }
}