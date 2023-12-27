package com.cgvsu;

import com.cgvsu.deleter.Deleter;
import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String RESOURCES_DIRECTORY = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(RESOURCES_DIRECTORY + "Test04Deleted234.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        Scanner scanner = new Scanner(System.in);


        List<Integer> verticesToDelete;

        while (true) {
            System.out.print("какие вершины удалить: ");
            String input = scanner.nextLine();
            verticesToDelete = Deleter.getVerticesToDeleteFromString(input);
            if (verticesToDelete == null) {
                System.err.println("подумайте ещё раз");
                System.err.println();
            } else {
                break;
            }
            System.out.println();

        }
        
        Model modelRes = Deleter.getModelWithDeletedVertices(model, verticesToDelete);

        ObjWriter.write(modelRes, RESOURCES_DIRECTORY + "outputTest04.obj");
    }
}
