package com.josi;

import com.josi.repository.MysqlDatabase;
import com.josi.service.Program;

public class Main {
    public static void main(String[] args) {
        Program program = new Program(new MysqlDatabase());
        program.start();
    }
}
