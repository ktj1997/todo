package hexagonal.todo.adapter.web.config;

import static java.time.format.DateTimeFormatter.ofPattern;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class DateTimeConfiguration implements Jackson2ObjectMapperBuilderCustomizer {

  private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
  private String dateFormat = "yyyy-MM-dd";
  private String timeFormat = "HH:mm";

  @Override
  public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
    jacksonObjectMapperBuilder
        .serializers(
            new LocalDateSerializer(ofPattern(dateFormat)),
            new LocalTimeSerializer(ofPattern(timeFormat)),
            new LocalDateTimeSerializer(ofPattern(dateTimeFormat))
        ).deserializers(
            new LocalDateDeserializer(ofPattern(dateFormat)),
            new LocalTimeDeserializer(ofPattern(timeFormat)),
            new LocalDateTimeDeserializer(ofPattern(dateTimeFormat))
        );
  }
}
