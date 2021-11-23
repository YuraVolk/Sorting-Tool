package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class WordParser extends DataParser {
    private final List<String> words = new ArrayList<>();

    @Override
    public void readInput() {
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
    }

    @Override
    String sort() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Total words: %d.\nSorted data: ", words.size()));
        words.sort(String::compareTo);
        words.forEach(e -> {
            builder.append(e);
            builder.append(" ");
        });
        return builder.toString();
    }

    @Override
    String sortOnCount() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Total words: %d.\n", words.size()));
        List<Map.Entry<String, Integer>> sorted = sortByCount(words, String::compareTo);
        sorted.forEach(e -> builder.append(String.format("%s: %d time(s), %d%%\n", e.getKey(), e.getValue(),
                (long) e.getValue() * 100 / sorted.size())));

        return builder.toString();
    }

    @Override
    void setInput(String input) {
        List<String> inputValues = Arrays.asList(input.split("\\s+"));
        words.addAll(inputValues);
    }
}
