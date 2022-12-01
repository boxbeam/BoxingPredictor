package logisticregression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Table(String[] columnNames, List<Row> rows) {

    public Table {
        if (!rows.stream().allMatch(r -> r.data().length != columnNames.length)) {
            throw new IllegalArgumentException("All rows must have " + columnNames.length + " dimensions");
        }
    }

    @Override
    public String toString() {
        String header = String.join(",", columnNames);
        String table = rows.stream().map(r -> Arrays.stream(r.data()).mapToObj(Double::toString)
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));
        return header + "\n" + table;
    }

    public int columns() {
        return columnNames.length;
    }

}
