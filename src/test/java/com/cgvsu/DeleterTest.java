package com.cgvsu;

import com.cgvsu.deleter.Deleter;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DeleterTest {
    public static final String RESOURCES_DIRECTORY = "src/test/resources/";

    @Test
    void removeVerticesTest1() throws IOException {
        Path fileName = Path.of(RESOURCES_DIRECTORY + "Test05.obj");
        String fileContent = Files.readString(fileName);

        Model model = ObjReader.read(fileContent);

        /*for (int i = model.polygons.size() - 1; i >= 0; i--) {
            Polygon polygon = model.polygons.get(i);

        }*/

        List<Integer> verticesDelete = new ArrayList<>(Arrays.asList(1, 2));

        Model updatedModel = Deleter.removeVerticesFromModel(model.clone(), verticesDelete);
        Assertions.assertNotEquals(model, updatedModel);


      //  System.out.println(new Vector3f(666f,1488f,0f));
    }

    @Test
    public void removeVerticesTest2() throws IOException {
        Path fileName = Path.of(RESOURCES_DIRECTORY + "Test05.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);

        model.vertices.forEach(System.out::println);


        List<Integer> verticesDelete = new ArrayList<>();
        verticesDelete.add(1);

        Model updatedModel = Deleter.removeVerticesFromModel(model.clone(), verticesDelete);
        updatedModel.vertices.forEach(System.out::println);

        // List<Integer> =
        List<Vector3f> expectedVertices = new ArrayList<>();
        expectedVertices.add(new Vector3f(0f,0f,0f));
        expectedVertices.add(new Vector3f(0f,1f,1f));
        expectedVertices.add(new Vector3f(1f,1f,1f));
        expectedVertices.add(new Vector3f(1f,0f,1f));
        expectedVertices.add(new Vector3f(1f,0f,0f));

        expectedVertices.forEach(System.out::println);

        List<Polygon> expectedModelPolygons = new ArrayList<>();
        System.out.println(model.polygons);

        System.out.println(updatedModel.polygons);

        // Проверяем вершины
        Assertions.assertEquals(expectedVertices, updatedModel.vertices);

        // Проверяем полигоны
        Assertions.assertEquals(expectedModelPolygons, updatedModel.polygons);
    }

    @Test
    public void removeVerticesTest3() throws IOException {
        Path fileName = Path.of(RESOURCES_DIRECTORY + "NonManifold2.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);

        model.vertices.forEach(System.out::println);


        List<Integer> verticesDelete = new ArrayList<>();
        verticesDelete.add(2);

        Model updatedModel = Deleter.removeVerticesFromModel(model.clone(), verticesDelete);
        updatedModel.vertices.forEach(System.out::println);

        // List<Integer> =
        List<Vector3f> expectedVertices = new ArrayList<>();
        expectedVertices.add(new Vector3f(1f,0f,0f));
        expectedVertices.add(new Vector3f(2f,0f,0f));
        expectedVertices.add(new Vector3f(2f,2f,0f));
        expectedVertices.add(new Vector3f(1f,2f,0f));
        expectedVertices.add(new Vector3f(0f,2f,0f));
        expectedVertices.add(new Vector3f(0f,1f,0f));
        expectedVertices.add(new Vector3f(1f,1f,0f));
        expectedVertices.add(new Vector3f(1.5f,0.5f,1f));



        expectedVertices.forEach(System.out::println);

        System.out.println(model.polygons);

        System.out.println(updatedModel.polygons);

        // Проверяем вершины
        Assertions.assertEquals(expectedVertices, updatedModel.vertices);

        // Проверяем полигоны
        Assertions.assertEquals(3, updatedModel.polygons.size());
    }

    @Test
    public void removeVerticesTest5() throws IOException {
        Path fileName = Path.of(RESOURCES_DIRECTORY + "Test04.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);

        List<Integer> verticesToDelete = new ArrayList<>();
        verticesToDelete.add(2);
        verticesToDelete.add(3);
        verticesToDelete.add(4);


        Model updatedModel = Deleter.removeVerticesFromModel(model.clone(), verticesToDelete);

        /// Проверка вершин
        List<Vector3f> expectedVertices = new ArrayList<>();
        expectedVertices.add(new Vector3f(1.0f, 0.0f, 0.0f));
        expectedVertices.add(new Vector3f(2.0f, 0.0f, 0.0f));
        expectedVertices.add(new Vector3f(0.0f, 2.0f, 0.0f));
        expectedVertices.add(new Vector3f(0.0f, 1.0f, 0.0f));
        expectedVertices.add(new Vector3f(1.0f, 1.0f, 0.0f));
        expectedVertices.add(new Vector3f(1.5f, 0.5f, 1.0f));
        expectedVertices.add(new Vector3f(1.0f, 2.0f, 3.0f));

        Assertions.assertEquals(expectedVertices, updatedModel.vertices);

        ObjWriter.write(updatedModel, RESOURCES_DIRECTORY + "Test04Deleted234.obj");

        // Проверяем полигоны
        Assertions.assertEquals(2, updatedModel.polygons.size());
    }
}