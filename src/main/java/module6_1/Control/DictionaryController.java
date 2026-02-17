package module6_1.Control;

import module6_1.Model.Dictionary;

public class DictionaryController {
    private Dictionary dictionary;

    public DictionaryController() {
        dictionary = new Dictionary();

        // Väliaikaisesti kovakoodataan sanoja
        dictionary.addWord("java", "A programming language.");
        dictionary.addWord("car", "A vehicle with four wheels.");
        dictionary.addWord("book", "A collection of written pages.");
    }

    // lisätän omia sanoja (ei tehtävä vaati)
    public String addWord(String word, String meaning) {
        if (word == null || word.trim().isEmpty()) {
            return "Word cannot be empty.";
        }
        if (meaning == null || meaning.trim().isEmpty()) {
            return "Meaning cannot be empty.";
        }
        dictionary.addWord(word.toLowerCase(), meaning);
        return "Word added successfully!";
    }

    public String searchWord(String word) {
        try {
            return dictionary.search(word);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
