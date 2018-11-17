import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    // Don't modify this method.
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.
    public static String[] toReadFile(String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    public static BinarySearchST<String, Integer> loadDictionary(String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        // your code goes here
        String[] words = toReadFile(file);
        for (int i = 0; i < words.length; i++) {
            String original = words[i].toLowerCase();
            if (st.contains(original)) {
                st.put(original, st.get(original) + 1);
            } else {
                st.put(original, 1);
            }
        } 
        return st;
    }

}

class T9 {
    private TST<Integer> tst;
    
    public T9(BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST<Integer>();
        for (String str : st.keys()) {
            tst.put(str, st.get(str));
        }
    }

    // get all the prefixes that match with given prefix.
    public Iterable<String> getAllWords(String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }

    public Iterable<String> potentialWords(String t9Signature) {
        // your code goes here
        ArrayList<String> wordlist = new ArrayList<>();
        for(String each : tst.keys()) {
            String[] strarr = each.split("");
            String num = "";
            for(String str : strarr){
                if(str.equals("a") || str.equals("b") || str.equals("c")) {
                    num = num + "2";
                }
                if(str.equals("d") || str.equals("e") || str.equals("f")) {
                    num = num + "3";
                }
                if(str.equals("g") || str.equals("h") || str.equals("i")) {
                    num = num + "4";
                }
                if(str.equals("j") || str.equals("k") || str.equals("l")) {
                    num = num + "5";
                }
                if(str.equals("m") || str.equals("n") || str.equals("o")) {
                    num = num + "6";
                }
                if(str.equals("p") || str.equals("q") || str.equals("r") || str.equals("s")) {
                    num = num + "7";
                }
                if(str.equals("t") || str.equals("u") || str.equals("v")) {
                    num = num + "8";
                }
                if(str.equals("w") || str.equals("x") || str.equals("y") || str.equals("z")) {
                    num = num + "9";
                }
            }
            if(num.equals(t9Signature)) {
                wordlist.add(each);
            }
        } 
        return wordlist;
    }

    // return all possibilities(words), find top k with highest frequency.
    public Iterable<String> getSuggestions(Iterable<String> words, int k) {
        // your code goes here
        ArrayList<String> wordlist = new ArrayList<>();
        MaxPQ<Integer> maxpq = new MaxPQ<>();
        for (String str : words) {
            maxpq.insert(tst.get(str));
        }
        for (int i = 0; i < k; i++) {
            int freq = maxpq.delMax();
            for (String s : words) {
                //maxpq.insert(tst.get(s));
                if (freq == tst.get(s)) {
                    wordlist.add(s);
                }
            }
        }
        Collections.sort(wordlist);
        return wordlist;
    }

    // final output
    // Don't modify this method.
    public Iterable<String> t9(String t9Signature, int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}

