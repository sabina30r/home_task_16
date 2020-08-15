package nix.edu.mapper;

import nix.edu.parser.CsvTable;

import java.util.List;

public interface CsvMapper {
    <T> List<T> map(CsvTable csvTable, Class<T> type);
}
