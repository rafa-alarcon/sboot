package com.example.demo.batch.steps;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.example.demo.domain.Student;



@Configuration
public class Step1 {
    @Bean
    public FlatFileItemReader<Student> fileReader(@Value("${input}") Resource in) throws Exception {

        return new FlatFileItemReaderBuilder<Student>()
                .name("file-reader")
                .resource(in)
                .targetType(Student.class)
                .linesToSkip(1)
                .delimited().delimiter(",").names(new String[]{"firstName", "lastName",  "email", "age"})
                .build();
    }
    
    @Bean
    public ItemWriter<Student> fileWriter(@Value("${output}") Resource resource) {
    	BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"firstName", "lastName", "age", "email"});
        fieldExtractor.afterPropertiesSet();
        DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter("|");
        lineAggregator.setFieldExtractor(fieldExtractor);
        return new FlatFileItemWriterBuilder<Student>()
                .name("file-writer")
                .resource(resource)
                .lineAggregator(lineAggregator)
                .build();
    }

   
}