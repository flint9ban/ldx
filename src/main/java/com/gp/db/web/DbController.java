package com.gp.db.web;

import com.gp.db.domain.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liyi on 2016/2/18.
 */
@Controller
@RequestMapping(value="db")
public class DbController {

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Resource resource =  resourceLoader.getResource("classpath:tables");
        model.addAttribute("tables",resource.getFile().list());
        return "db/index";
    }

    @RequestMapping(value="{table}")
    @ResponseBody
    public List<Column> detail(@PathVariable("table") String table) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:tables");
        File[] files = resource.getFile()
                .listFiles((file,name)->name.equals(table));
        if(files.length>0){
            File file = files[0];
            return Files.lines(Paths.get(file.getPath()+"/table")).map(r->{return new Column(r);}).collect(Collectors.toList());
        }
        return null;
    }


}
