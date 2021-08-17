package com.obss.jss;

import com.obss.jss.Entity.Book;
import com.obss.jss.Entity.BookLibrary;
import com.obss.jss.Entity.JavaBookLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration //@SpringBootApplication
public class Week3Demo1Application {

    public static void main(String[] args) {

        //SpringApplication.run(Week3Demo1Application.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Week3Demo1Application.class);
        BookLibrary library = context.getBean(BookLibrary.class);
        List<Book> list = library.search("asd");

        for (Book book : list) {
            System.out.println(book.getName());
        }
    }

    @Bean
    public BookLibrary bookLibrary() {
        return new JavaBookLibrary();
    }

}
