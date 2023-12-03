package com.cgvsu.objreader;

public class ObjReaderException extends RuntimeException {
    public ObjReaderException(String errorMessage) {
        super("Error parsing OBJ file on line: " + ". " + errorMessage);
    }
}