package com.development.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

@Service
public interface FindCommonWordsService {

    HashMap<String, Integer> findWords(String path) throws IOException;
}
