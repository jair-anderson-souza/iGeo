/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 27, 2017 7:59:25 PM
 */
@Deprecated
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    public LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        return LocalDate.parse(jp.readValueAs(String.class));
    }

}
