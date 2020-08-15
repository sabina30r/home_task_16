package nix.edu;


import nix.edu.data.Gender;
import nix.edu.data.PersonalInfo;
import nix.edu.mapper.impl.CsvAnnotationMapper;
import nix.edu.parser.CsvTable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvMapperTest {

    private static CsvTable table;
    private static CsvAnnotationMapper mapper;

    @BeforeAll
    static void init() throws IOException, URISyntaxException {
        Path source = Paths.get(Main.class.getResource("/source.csv").toURI());
        table = CsvTable.fromFile(source).get();
        mapper = new CsvAnnotationMapper();
    }

    @Test
    void testMappingCsvToList() throws IOException, URISyntaxException {
        init();
        List<PersonalInfo> personalInfoList = mapper.map(table, PersonalInfo.class);
        PersonalInfo personalInfo = personalInfoList.get(0);
        PersonalInfo personalInfo1 = personalInfoList.get(1);
        assertEquals("Mike", personalInfo.getName());
        assertEquals("Beth", personalInfo1.getName());
        assertEquals(27, personalInfo.getAge());
        assertEquals(23, personalInfo1.getAge());
        assertEquals(Gender.MALE, personalInfo.getGender());
        assertEquals(Gender.FEMALE, personalInfo1.getGender());
        assertEquals("janitor", personalInfo.getOccupation());
        assertEquals("recruiter", personalInfo1.getOccupation());
    }

}
