
package ruzzlesolver;

import java.util.Scanner;

public class Dictionary
{
    private StringHashTable words;
    private StringHashTable prefixes;
    private final String DICTIONARY_FILE_PATH = "/dictionary.txt";

    public Dictionary()
    {
        words = new StringHashTable();
        prefixes = new StringHashTable();

        
        Scanner fileScanner = new Scanner(getClass().getResourceAsStream(DICTIONARY_FILE_PATH));
         while(fileScanner.hasNextLine()) addToDictionary(fileScanner.nextLine());
        
        
    }

    private void addToDictionary(String in)
    {
        in = in.trim().toLowerCase();
        words.insert(in);
        for(int x = 1; x < in.length() - 1; x++)
        {
            prefixes.insert(in.substring(0,x));
        }
    }

    public boolean isWord(String in)
    {
        return words.exists(in);
    }

    public boolean isPrefix(String in)
    {
        return prefixes.exists(in);
    }
}
