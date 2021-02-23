import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Spellchecker {

    public static class Node {
        Node[] Arr;
        boolean check;

        void isWord(boolean val) {
            this.check = val;
        }

        public Node() {
            this.Arr = new Node[26];
            for (int i = 0; i < LETTERS.length; i++) {
                Arr[i] = null;
            }
            this.check = false;
        }
    }

    public Node root;
    public Node crtNode;

    public static char[] LETTERS = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'r', 'q', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z'
    };

    public Spellchecker() {
        root = new Node();
    }

    int getIndex(char x) {
        for (int i = 0; i < LETTERS.length; i++) {
            if (LETTERS[i] == x) {
                return i;
            }

        }
        return -1;
    }

    void addWord(String word) {
        crtNode = root;
        for (int i = 0; i < word.length(); i++) {

            int index = word.charAt(i) - 'a';


            if (crtNode.Arr[index] == null) {
                crtNode.Arr[index] = new Node();
            }

            crtNode = crtNode.Arr[index];
        }

        crtNode.isWord(true);
    }

    private boolean containsWord(String word) {
        crtNode = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (crtNode.Arr[index] == null)
                return false;

            crtNode = crtNode.Arr[index];
        }
        return (crtNode != null && crtNode.check);
    }


    int countWords(Node crtNode) {
        int count = 0;

        if (crtNode.check) {
            count++;
        }

        for (int i = 0; i < LETTERS.length; i++) {
            if (crtNode.Arr[i] != null) {
                count += countWords(crtNode.Arr[i]);

            }
        }

        return count;
    }

    void addFile(String filename) {
        File dict = new File(filename);
        Scanner scan = null;
        try {
            scan = new Scanner(dict);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String[] words = scan.nextLine().split("[^A-Za-z]");
            for (String s : words) {
                String word = s;
                word = word.strip();

                if (!word.equals("")) {
                    addWord(word.toLowerCase());
                }

            }
        }

        scan.close();
    }

    int checkFile(String filename) {
        int count = 0;
        File dict = new File(filename);
        Scanner scan = null;
        try {
            scan = new Scanner(dict);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String[] words = scan.nextLine().split("[^A-Za-z]");
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                word = word.strip();

                if (!word.equals("")) {
                    if (!containsWord(word.toLowerCase())) {
                        count++;
                        System.out.println(word);
                    }
                }

            }
        }

        scan.close();
        return count;
    }

    public static void main(String[] args) {
        String dict = "dictionary.txt";
        String sample = "sample.txt";

        Spellchecker spell = new Spellchecker();
        spell.addFile(dict);

        System.out.println(spell.countWords(spell.root));
        System.out.println(spell.checkFile(sample));
    }
}
