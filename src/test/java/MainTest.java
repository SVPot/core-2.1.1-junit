
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private static long suiteStartTime;
    private long testStartTime;

    private List<Employee> employeeList = Arrays.asList(
            new Employee(1, "John", "Smith", "USA", 25),
            new Employee(2, "Ivan", "Petrov", "RU", 23));

    private String jsonExpected = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"Smith\",\n" +
            "    \"country\": \"USA\",\n" +
            "    \"age\": 25\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"firstName\": \"Ivan\",\n" +
            "    \"lastName\": \"Petrov\",\n" +
            "    \"country\": \"RU\",\n" +
            "    \"age\": 23\n" +
            "  }\n" +
            "]";

    private String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
    private String fileName = "data.csv";
    private String jsonExpectedFile = "data.json";
    private String jsonFile = "data.json";
    private String xmlFileName = "data.xml";

    @BeforeAll
    public static void initTests() {
        System.out.println("Тестирование методов класса Main.");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeTests() {
        System.out.println("Тестирование методов класса Main завершено: "
                + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Запускаем очередной тест...");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void completeTest() {
        System.out.println("Тест завершен: "
                + (System.nanoTime() - testStartTime));
    }

    @Test
    @DisplayName("Тест метода parseCSV")
    public void testParseCSV() {
        List<Employee> expected = employeeList;
        List<Employee> actual = Main.parseCSV(columnMapping, fileName);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест метода listToJson")
    public void testListToJson() {
        String expected = jsonExpected;

        String actual = Main.listToJson(employeeList);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест метода writeString")
    public void testWriteString() throws IOException {
        File expectedJsonFile = new File(jsonExpectedFile);

        Main.writeString("data_test.json", jsonExpected);
        File actualJsonFile = new File("data_test.json");

        assertTrue(FileUtils.contentEquals(expectedJsonFile, actualJsonFile));
    }

    @Test
    @DisplayName("Тест метода parseXML")
    public void testParseXML() {
        List<Employee> expected = employeeList;
        List<Employee> actual = Main.parseXML(xmlFileName);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест метода readString")
    public void testReadString() {
        String expected = "[  {    \"id\": 1,    \"firstName\": \"John\",    \"lastName\": \"Smith\",    \"country\": \"USA\",    \"age\": 25  },  {    \"id\": 2,    \"firstName\": \"Ivan\",    \"lastName\": \"Petrov\",    \"country\": \"RU\",    \"age\": 23  }]";
        String actual = Main.readString(jsonFile);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест jsonToList")
    public void testJsonToList() {
        List<Employee> expected = employeeList;
        List<Employee> actual = Main.jsonToList(jsonExpected);
        Assertions.assertEquals(expected, actual);
    }

}
