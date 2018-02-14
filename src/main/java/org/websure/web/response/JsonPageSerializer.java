package org.websure.web.response;

import java.io.IOException;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonPageSerializer extends JsonSerializer<Page<?>> {

	@Override
	public void serialize(Page<?> page, JsonGenerator jsonGen, SerializerProvider provider) throws IOException {

        ObjectMapper om = new ObjectMapper()
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .setSerializationInclusion(Include.NON_EMPTY);
        jsonGen.writeStartObject();
        jsonGen.writeFieldName("content");
        jsonGen.writeRawValue(om.writerWithView(provider.getActiveView())
                .writeValueAsString(page.getContent()));        
        jsonGen.writeFieldName("size");
        jsonGen.writeNumber(page.getSize());
        jsonGen.writeFieldName("number");
        jsonGen.writeNumber(page.getNumber());
        jsonGen.writeFieldName("totalElements");
        jsonGen.writeNumber(page.getTotalElements());
        jsonGen.writeFieldName("last");
        jsonGen.writeBoolean(page.isLast());
        jsonGen.writeFieldName("totalPages");
        jsonGen.writeNumber(page.getTotalPages());
        //jsonGen.writeObjectField("sort", page.getSort());
        jsonGen.writeFieldName("first");
        jsonGen.writeBoolean(page.isFirst());
        jsonGen.writeFieldName("numberOfElements");
        jsonGen.writeNumber(page.getNumberOfElements());
 
        jsonGen.writeEndObject();		
	}

}
