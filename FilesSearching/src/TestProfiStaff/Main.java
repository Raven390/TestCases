package TestProfiStaff;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class Main {

    public static void main(String[] args) {
	    String directoryName = "D:\\AAA";
	    String finalFileName = "D:\\AAA\\product.txt";

        FilenameFilter txtFilter = (File dir, String name) -> (new File(dir.getAbsolutePath()
                + File.separator + name).isDirectory()) || name.toLowerCase().endsWith(".txt");

        List<File> textFilesList = getFileName(new File(directoryName), new ArrayList<>(), txtFilter);
        textFilesList.sort(new SortFileName());
        String resultText = fileWriter(textFilesList);
        resultFileWriter(finalFileName, resultText);
    }

    public static List<File> getFileName(File directory, List<File> fileNameList, FilenameFilter txtFilter) {
        for (File file : Objects.requireNonNull(directory.listFiles(txtFilter))) {
            if (file.isDirectory()) {
                getFileName(file, fileNameList, txtFilter);
            } else {
                fileNameList.add(file);
            }
        }
        return fileNameList;
    }

    public static String fileWriter(List<File> textFileNameList) {
        final StringBuilder allText = new StringBuilder();
         for (File file : textFileNameList) {
             try {
                 FileReader fr = new FileReader(file);
                 BufferedReader br = new BufferedReader(fr);
                 String line = br.readLine();
                 while (line != null) {
                     allText.append(line).append("\n");
                     line = br.readLine();
                 }
             } catch (IOException e) {
                 System.out.println(e.getMessage());
             }
         }
         return allText.toString();
    }

    public static void resultFileWriter(String fileName, String resultText) {
        try (BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bof.write(resultText.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class SortFileName implements Serializable, Comparator<File> {

    @Override
    public int compare(File file1, File file2) {
        return file1.getName().compareTo(file2.getName());
    }

}