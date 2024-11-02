package io.github.eealba.jasoner.internal;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;

import static io.github.eealba.jasoner.internal.Helper.readResource;
import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    @Test
    void write_object() throws IOException {
        var tokenWriter = new JsonWriter(new StringWriter(), false);
        tokenWriter.append(TokenImpl.OBJECT_START);
        tokenWriter.append(TokenImpl.OBJECT_END);
        assertEquals("{}", tokenWriter.writertoString());
    }
    @Test
    void write_object_2() throws IOException {
        var tokenWriter = new JsonWriter(new StringWriter(), false);
        String json = """
                {"name":"John","lastName":"Doe","age":30,"address":"New York","address2":"New Jersey","profession":"Developer"}""";
        JsonTokenizer tokenizer = new JsonTokenizerImpl(json);
        while (tokenizer.hasNext()) {
            tokenWriter.append(tokenizer.next());
        }
        assertEquals(json, tokenWriter.writertoString());
    }
    @Test
    void write_plan_pretty_false() throws IOException, URISyntaxException {
        var tokenWriter = new JsonWriter(new StringWriter(), false);
        String json = readResource("plan_request_POST_no_indent.json");
        assertFalse(json.contains("\n"));

        JsonTokenizer tokenizer = new JsonTokenizerImpl(json);
        while (tokenizer.hasNext()) {
            tokenWriter.append(tokenizer.next());
        }

        assertEquals(json, tokenWriter.writertoString());
    }

    @Test
    void write_plan_pretty_true() throws IOException, URISyntaxException {
        var tokenWriter = new JsonWriter(new StringWriter(), true);
        String json = readResource("plan_request_POST_pretty.json");
        assertTrue(json.contains("\n"));

        JsonTokenizer tokenizer = new JsonTokenizerImpl(json);
        while (tokenizer.hasNext()) {
            tokenWriter.append(tokenizer.next());
        }
        String result = tokenWriter.writertoString();
        System.out.println(result);
        assertEquals(json, result);
    }

}