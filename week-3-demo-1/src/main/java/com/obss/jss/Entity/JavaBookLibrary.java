package com.obss.jss.Entity;

import java.util.ArrayList;
import java.util.List;

public class JavaBookLibrary implements BookLibrary{
    @Override
    public List<Book> search(String name) {
        List<Book> list = new ArrayList<>();
        list.add(new Book("asd", 12));

        return list;
    }
}
