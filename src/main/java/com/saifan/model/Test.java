package com.saifan.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ma on 2015/9/9.
 */
public class Test {

    public static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    public static void main(String[] args)
    {
        add(1, 2);
    }

    public static int add(int a , int b)
    {
        logger.entry(a, b);
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");
        logger.printf(Level.TRACE, "%d+%d=%d", a, b, a + b);
        logger.exit(a + b);
        return a + b;
    }
}
