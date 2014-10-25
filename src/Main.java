import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args, File[] cmdArg) {

//        System.out.println("Please, enter filename: ");
//        Scanner scanner = new Scanner(System.in);
//        String fileName = scanner.nextLine();
        String fileName = "myfile.txt";
        Map<String, Integer> words = new HashMap<>();
        try (Reader r = new InputStreamReader(new BufferedInputStream(new FileInputStream(fileName)))) {
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = r.read()) >= 0) {
                char curChar = (char) ch;
                if (Character.isLetterOrDigit(curChar)) {
                    sb.append(curChar);
                } else {
                    addWord(words, sb);
                    sb.setLength(0);
                }
            }
            addWord(words, sb);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(words.entrySet());
        Collections.sort(sortedWords, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int value = -o1.getValue().compareTo(o2.getValue());
                if (value == 0) {
                    value = o1.getKey().compareTo(o2.getKey());
                }
                return value;
            }
        });

        try (Writer writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(cmdArg[1])))) {

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    private static boolean addWord(Map<String, Integer> words, StringBuilder sb) {
        if (sb.length() > 0) {
            String key = sb.toString();
            Integer count = words.get(key);
            if (count == null) {
                count = 0;
            }
            words.put(key, 1 + count);
        }
        return false;
    }

}
