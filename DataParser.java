package sorting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class DataParser {
    protected Scanner scanner = new Scanner(System.in);

    abstract void readInput();
    abstract String sort();
    abstract String sortOnCount();
    abstract void setInput(String input);

    protected <T> List<Map.Entry<T, Integer>> sortByCount(List<T> elements, Comparator<? super T> comparator) {
        elements.sort(comparator);
        Map<T, Integer> frequencyMap = new LinkedHashMap<>();

        for (T element : elements) {
            int previous = 0;
            if (frequencyMap.get(element) != null) {
                previous = frequencyMap.get(element);
            }
            frequencyMap.put(element, ++previous);
        }

        Stream<Map.Entry<T,Integer>> sorted =
                frequencyMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue());
        return sorted.collect(Collectors.toList());
    }
}
