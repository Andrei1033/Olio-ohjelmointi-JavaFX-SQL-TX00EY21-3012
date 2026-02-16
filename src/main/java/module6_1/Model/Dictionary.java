package module6_1.Model;

import java.util.HashMap;

public class Dictionary {
    private HashMap<String, String> words;

    public Dictionary() {
        words = new HashMap<>();
    }

    // Lisää sana ja sen merkitys
    public void addWord(String word, String meaning) {
        words.put(word.toLowerCase(), meaning);
    }

    // Hae sanan merkitys
    public String search(String word) throws Exception{
        if (word == null || word.trim().isEmpty()) {
            throw new Exception("Word cannot be empty.");
        }

        String meaning = words.get(word.toLowerCase());

        if (meaning == null) {
            throw new Exception("Word not found.");
        }

        return meaning;
    }
}
