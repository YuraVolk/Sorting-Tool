package sorting;

import java.util.*;

public class LineParser extends DataParser {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void readInput() {
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
    }

    @Override
    String sort() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Total lines: %d.\nSorted data: ", lines.size()));
        lines.sort(String::compareTo);
        lines.forEach(e -> {
            builder.append(e);
            builder.append(" ");
        });

        return builder.toString();
    }

    @Override
    String sortOnCount() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Total lines: %d.\n", lines.size()));
        List<Map.Entry<String, Integer>> sorted = sortByCount(lines, String::compareTo);
        sorted.forEach(e -> builder.append(String.format("%s: %d time(s), %d%%\n", e.getKey(), e.getValue(),
                (long) e.getValue() * 100 / sorted.size())));

        return builder.toString();
    }

    @Override
    void setInput(String input) {
        List<String> inputValues = Arrays.asList(input.split("\n"));
        lines.addAll(inputValues);
    }
}
