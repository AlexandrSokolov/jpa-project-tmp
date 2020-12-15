#### Usage:

1. Share test files:

    `InputStream sharedFile = testInputStream("shared.test.xlsx");`

2. Write into test files via `OutputStream`:

    ```
    File tempFile = tempTestFile(TEST_EXCEL_FILE_NAME);
    try (FileOutputStream fos = new FileOutputStream(tempFile)) {
      service.writeIntoFile(fos);
      System.out.println(tempFile.getAbsolutePath());
    }
    ```
3. It enables logging for REST and SOAP via the method, annotated by `@BeforeClass`

#### Requirements to use the `commons_test` module:

1. add `test` scope dependency on `commons_test` module in your module `pom.xml`:

    ```
    <dependency>
      <groupId>com.a.b</groupId>
      <artifactId>jpa-project-commons-test</artifactId>
      <version>${project.version}</version>
      <scope>test</scope>
    </dependency>
    ```

2. Extend your test with `BaseTest`:

```
import com.a.b.commons.test.BaseTest;

public class MyTest extends BaseTest { ...
```