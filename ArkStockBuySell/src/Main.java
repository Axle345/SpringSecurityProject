import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/main/java/com/project/SpringSecurity/test.pdf";

        String text = readPdf(fileName);

        ArrayList<String> stockList = getStockList(text);

        writeToFile(stockList);
    }

    public static String readPdf(String fileName) throws IOException {
        File file = new File(fileName);
        PDDocument doc = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(doc);
        doc.close();
        return text;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void writeToFile(ArrayList<String> list) throws IOException {
        FileWriter writer = new FileWriter("output.txt", true);
        for (String str : list) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    public static ArrayList<String> getStockList(String text) {
        String lines[] = text.split("\\r?\\n");
        ArrayList<String> stockList = new ArrayList<>();
        int i = 3;
        while (i > 0) {
            String currentLine[] = lines[i].split("\\s+");
            if (isInteger(currentLine[0])) {
                stockList.add(currentLine[2]);
                i++;
            } else {
                i = 0;
            }
        }
        return stockList;
    }
}