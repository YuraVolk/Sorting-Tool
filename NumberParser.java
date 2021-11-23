package sorting;

import java.util.*;

public class NumberParser extends DataParser {
    private final List<Integer> numbers = new ArrayList<>();

    @Override
    public void readInput() {
        while (scanner.hasNext()) {
            String inputValue = scanner.next();
            if (inputValue.matches("-?\\d+")) {
                numbers.add(Integer.valueOf(inputValue, 10));
            } else {
                System.out.printf("\"%s\" is not a long. It will be skipped.", inputValue);
            }
        }
    }

    @Override
    String sort() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Total numbers: %d.\nSorted data: ", numbers.size()));
        numbers.sort(Integer::compareTo);
        numbers.forEach(e -> {
            builder.append(e);
            builder.append(" ");
        });

        return builder.toString();
    }

    @Override
    String sortOnCount() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Total numbers: %d.\n", numbers.size()));
        List<Map.Entry<Integer, Integer>> sorted = sortByCount(numbers, Integer::compareTo);
        sorted.forEach(e -> builder.append(String.format("%s: %d time(s), %d%%\n", e.getKey(), e.getValue(),
                (long) e.getValue() * 100 / sorted.size())));

        return builder.toString();
    }

    @Override
    void setInput(String input) {
        List<String> inputValues = Arrays.asList(input.split("\\s+"));
        inputValues.forEach(v -> {
            if (v.matches("-?\\d+")) {
                numbers.add(Integer.valueOf(v, 10));
            } else {
                System.out.printf("\"%s\" is not a long. It will be skipped.", v);
            }
        });
    }

}
