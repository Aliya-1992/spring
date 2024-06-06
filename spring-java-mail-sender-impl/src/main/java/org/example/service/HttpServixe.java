package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service

public class HttpServixe {



        @Autowired
        private HttpSession httpSession;

        public HttpSession getHttpSession() {
            return httpSession;
        }
    }

