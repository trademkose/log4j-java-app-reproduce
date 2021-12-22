package com.sensepost.log4jpwn;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static spark.Spark.*;

public class App {
    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {

        port(8080);

        get("/*", (req, res) -> {

            String ua = req.headers("User-Agent");
            String pwn = req.queryParams("pwn");
            String pth = req.pathInfo();

            System.out.println("logging User-Agent : " + ua);
            System.out.println("logging PWN : " + pwn);
            System.out.println("logging PATH : " + pth);

            // trigger
            logger.error("logging ua: " + ua);
            logger.error(ua);
            logger.error(pwn);
            logger.error(pth);
            String response_payload="{" 
            +"\"status\":\"" + "OK" + "\","
            +"\"ua\":\"" + ua + "\","
            +"\"pwn\":\"" + pwn + "\","
            +"\"pth\":\"" + pth + "\"" + " }";

            return response_payload;
        });
    }
}
