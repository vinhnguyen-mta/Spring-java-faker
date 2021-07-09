package com.Api;

import com.Dto.Book;
import com.Service.IBookService;
import com.Service.impl.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
public class BookController {
  @Autowired
  private IBookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books")
  public ResponseEntity<List<Book>> getTodos() {
    return ResponseEntity.ok(bookService.getBooks());
  }

  @Autowired
  private ObjectMapper objectMapper;
  @GetMapping("/persons")
  public JsonNode getRandomPersons() {

    Faker faker = new Faker();
    ArrayNode persons = objectMapper.createArrayNode();

    for (int i = 0; i < 10; i++) {
      persons.add(objectMapper.createObjectNode()
        .put("firstName", faker.name().firstName())
        .put("lastName", faker.name().lastName())
        .put("title", faker.name().title())
        .put("suffix", faker.name().suffix())
        .put("address", faker.address().streetAddress())
        .put("city", faker.address().cityName())
        .put("country", faker.address().country()));
    }

    return persons;
  }

  @GetMapping("/books")
  public JsonNode getRandomBook() {

    Faker faker = new Faker(new Locale("en-US"));
    ArrayNode books = objectMapper.createArrayNode();

    for (int i = 0; i < 10; i++) {
      books.add(objectMapper.createObjectNode()
        .put("author", faker.book().author())
        .put("genre", faker.book().genre())
        .put("publisher", faker.book().publisher())
        .put("title", faker.book().title()));
    }

    return books;
  }

  @GetMapping("/foods")
  public JsonNode getRandomFoods() {

    Faker faker = new Faker(new Locale("de"));
    ArrayNode foods = objectMapper.createArrayNode();

    for (int i = 0; i < 10; i++) {
      foods.add(objectMapper.createObjectNode()
        .put("ingredients", faker.food().ingredient())
        .put("spices", faker.food().spice())
        .put("measurements", faker.food().measurement()));
    }
    return foods;
  }

  @GetMapping("/{id}")
  public JsonNode getAPI(@PathVariable(name = "id") String thongtin){
    Faker faker = new Faker(new Locale("en-US"));
    ArrayNode books = objectMapper.createArrayNode();
    boolean sosanh = thongtin.equals("sach");
    if(sosanh==true ){
      for (int i = 0; i < 10; i++) {
        books.add(objectMapper.createObjectNode()
          .put("ingredients", faker.food().ingredient())
          .put("spices", faker.food().spice())
          .put("measurements", faker.food().measurement())
        );
      }
    }else{
      for (int i = 0; i < 10; i++) {
        books.add(objectMapper.createObjectNode()
          .put("author", faker.book().author())
          .put("genre", faker.book().genre())
          .put("publisher", faker.book().publisher())
          .put("title", faker.book().title()));
      }
    }
    return books;
  }


}
