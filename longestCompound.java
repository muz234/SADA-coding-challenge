import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
Ref: https://github.com/szn1992/Longest-Compound-Word/blob/master/src/LongestCompoundWord.java
Ref: https://github.com/szn1992/Longest-Compound-Word/blob/master/src/Trie.java
*/

public class longestCompound {

    static String [] words = new String[370077];
    public static void main(String[] args) {
        readFile();
       // String words[] = { "cat", "cats", "catsdogcats", "catxdogcatsrat", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcat", "ratcatdog", "ratcatdogcat" };
        // ratcatdogcat
        System.out.println("Longest Compound word : " + longestCompundWord(words));

    }

    public static void readFile() {
        String data= null;
        int i=0;
        try {
            // importing String data from .txt to String Array.
            File scObject = new File("/Users/muzaffarahmed/Desktop/Github/SADA/words.txt");
            Scanner read = new Scanner(scObject);
            while (read.hasNextLine()) {
                data = read.nextLine();
                // assigning String data to `words []`.
                words[i] = data;
                // System.out.println(words[i]);
                i++;
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    private static String longestCompundWord(String[] words) {
        // creating array list to assign strings data.
        ArrayList<String> wordList = new ArrayList<String>();
        // creating TreeMap to store String followed by length using key to compare between different nodes.
        TreeMap<String, Integer> wordTree = new TreeMap<String, Integer>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.length() - str2.length();
            }
        });

        for (int i = 0; i < words.length; i++) {
            wordTree.put(words[i], i);
            wordList.add(words[i]);
        }
        // word counts based similar Strings
        System.out.println(wordTree);
        return longCompundWord(wordTree, wordList);

    }

    // method to check if the words are compound.
    private static boolean isComWord(String word, ArrayList<String> wordList) {
        if (wordList.contains(word))
            return true;
        for (int i = 1; i < word.length(); i++) {
            //common word sorted in beginning.
            String prefix = word.substring(0, i);
            if (isComWord(prefix, wordList)) {
                //check remainder if it is a compound word.
                String remainWord = word.substring(i, word.length());
                if (remainWord.length() == 0)
                    return true;
                return isComWord(remainWord, wordList);
            }
        }
        return false;
    }

    // method to check the word is the longest compound
    public static String longCompundWord(TreeMap<String, Integer> wordTree, ArrayList<String> wordList) {
        while (wordTree.size() > 0) {
            String word = wordTree.lastKey();
            wordTree.remove(word);
            wordList.remove(word);
            if (isComWord(word, wordList))
                return word;
        }
        return "";
    }
}

