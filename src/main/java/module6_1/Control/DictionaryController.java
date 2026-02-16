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

    public String searchWord(String word) {
        try {
            return dictionary.search(word);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
