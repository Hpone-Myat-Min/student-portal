package uk.ac.leedsbeckett.hmm.student_portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Course;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.CourseRepository;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.StudentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class MiscellaneousBeans {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

}
