package nix.edu;

import nix.edu.data.PersonalInfo;
import nix.edu.mapper.impl.CsvAnnotationMapper;
import nix.edu.parser.CsvTable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path source = Paths.get(Main.class.getResource("/source.csv").toURI());
        CsvTable table = CsvTable.fromFile(source).get();
        CsvAnnotationMapper mapper = new CsvAnnotationMapper();
        List<PersonalInfo> personalInfos = mapper.map(table, PersonalInfo.class);
        System.out.println(personalInfos);

    }
}
