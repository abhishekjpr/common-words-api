package com.development.controller;

import com.development.service.FindCommonWordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping (value = "/")
@Slf4j
public class FindCommonWordsController {

    @RequestMapping (value = "find-words", method = RequestMethod.POST)
    public Map<String, Integer> getCommonWords(@RequestParam(name= "path") String path){
        if(!StringUtils.isEmpty(path)) {
            log.info("[{}][getCommonWords] Received Path : {}", CLASS, path);
            try {
                HashMap<String, Integer> result = findCommonWordsService.findWords(path);
                return result;
            } catch (Exception e) {
                log.error("[{}][getCommonWords] Exception in reading file.", CLASS, e);
            }
        } else {
            log.info("[{}][getCommonWords] No path specified for the files.", CLASS);
        }
        return null;
    }

    private static final String CLASS = FindCommonWordsController.class.getSimpleName();

    @Autowired
    FindCommonWordsService findCommonWordsService;
}
