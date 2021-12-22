package vlang.interpreter.parsers.rowJsonParser;

import org.junit.jupiter.api.Test;
import vlang.GlobalSetting;

import static org.junit.jupiter.api.Assertions.*;

class RowJsonParserTest {

    @Test
    void parse() {
        boolean ok = GlobalSetting.parser.parse("src\\test\\resources\\test.txt", "src\\test\\resources\\out.json");
        GlobalSetting.log.warning("Parse " + ok);
    }
}