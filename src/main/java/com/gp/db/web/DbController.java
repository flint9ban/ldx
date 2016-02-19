package com.gp.db.web;

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

/**
 * Created by liyi on 2016/2/18.
 */
@Controller
@RequestMapping(value="db")
public class DbController {

    @Autowired
    private ResourceLoader resourceLoader;

//    @Autowired
//    protected AuthenticationManager authenticationManager;

    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
//        auth(request,"1","2");
        Resource resource =  resourceLoader.getResource("classpath:tables");
        model.addAttribute("tables",resource.getFile().list());
        return "db/index";
    }

    @RequestMapping(value="{table}")
    @ResponseBody
    public String detail(@PathVariable("table") String table) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:tables");
        File[] files = resource.getFile()
                .listFiles((file,name)->name.equals(table));
        return "yes";
    }

//    private void auth(HttpServletRequest request,String username,String password){
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//        // generate session if one doesn't exist
//        request.getSession();
//
//        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authenticatedUser = authenticationManager.authenticate(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
//    }
}
