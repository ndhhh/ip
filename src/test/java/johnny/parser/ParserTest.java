package johnny.parser;

import johnny.ui.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testDateParse() {
        Ui ui = new Ui();
        LocalDate date = Parser.parseDate("29/03/2024", ui);
        assertEquals("29/03/24", date);
    }
}
