package com.cgvsu.objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ObjWriter {
    private static final String OBJ_VERTEX_TOKEN = "v";
    private static final String OBJ_TEXTURE_TOKEN = "vt";
    private static final String OBJ_NORMAL_TOKEN = "vn";
    private static final String OBJ_FACE_TOKEN = "f";

    public ObjWriter() {}

    public static void write(Model model, String pathLine) throws IOException {
        // File cleaning
        Files.write(Paths.get(pathLine), "".getBytes());

        try {
            FileWriter writer = new FileWriter(pathLine, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            writeVertices(bufferedWriter, model.vertices);
            writeTextureVertices(bufferedWriter, model.textureVertices);
            writeNormals(bufferedWriter, model.normals);
            writeFaces(bufferedWriter, model.polygons);

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected static void writeVertices(BufferedWriter bufferedWriter,
                                        List<Vector3f> vertices) throws Exception {
        if (vertices.isEmpty()) {
            throw new Exception("OBJ-File is incorrect. Array of vertices is empty!");
        }
        for (Vector3f vertex : vertices) {
            bufferedWriter.write(OBJ_VERTEX_TOKEN + " " + vertex.getX() + " " + vertex.getY() + " " + vertex.getZ() + "\n");
        }
        bufferedWriter.write('\n');
    }
    protected static void writeTextureVertices(BufferedWriter bufferedWriter,
                                               List<Vector2f> textureVertices) throws Exception {
        if (textureVertices.isEmpty()) return;
        for (Vector2f vertex : textureVertices) {
            bufferedWriter.write(OBJ_TEXTURE_TOKEN + " " + vertex.getX() + " " + vertex.getY() + "\n");
        }
        bufferedWriter.write('\n');
    }
    protected static void writeNormals(BufferedWriter bufferedWriter,
                                       List<Vector3f> normals) throws Exception {
        if (normals.isEmpty()) return;
        for (Vector3f normal : normals) {
            bufferedWriter.write(OBJ_NORMAL_TOKEN + " " + normal.getX() + " " + normal.getY() + " " + normal.getZ() + "\n");
        }
    }
    protected static void writeFaces(BufferedWriter bufferedWriter,
                                     List<Polygon> polygons) throws Exception {
        if (polygons.isEmpty()) {
            throw new Exception("OBJ-File is incorrect. Array of polygons is empty!");
        }
        for (Polygon polygon : polygons) {
            bufferedWriter.write(OBJ_FACE_TOKEN + constructFace(polygon) + "\n");
        }
    }
    private static String constructFace(Polygon polygon) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        if (polygon.getTextureVertexIndices().isEmpty() && polygon.getNormalIndices().isEmpty()) {
            for (Integer vertexIndex : polygon.getVertexIndices()) {
                stringBuilder.append(" ").append(vertexIndex + 1);
            }
        } else if (polygon.getNormalIndices().isEmpty()) {
            if (polygon.getVertexIndices().size() == polygon.getTextureVertexIndices().size()) {
                for (int i = 0; i < polygon.getVertexIndices().size(); i++) {
                    stringBuilder.append(" ").append(polygon.getVertexIndices().get(i) + 1)
                            .append("/").append(polygon.getTextureVertexIndices().get(i) + 1);
                }
            } else {
                throw new Exception("Incorrect parameters for face!");
            }
        } else if (polygon.getTextureVertexIndices().isEmpty()) {
            if (polygon.getVertexIndices().size() == polygon.getNormalIndices().size()) {
                for (int i = 0; i < polygon.getVertexIndices().size(); i++) {
                    stringBuilder.append(" ").append(polygon.getVertexIndices().get(i) + 1)
                            .append("//").append(polygon.getNormalIndices().get(i) + 1);
                }
            } else {
                throw new Exception("Incorrect parameters for face!");
            }
        } else {
            if (polygon.getVertexIndices().size() == polygon.getTextureVertexIndices().size() &&
                    polygon.getTextureVertexIndices().size() == polygon.getNormalIndices().size()) {
                for (int i = 0; i < polygon.getVertexIndices().size(); i++) {
                    stringBuilder.append(" ").append(polygon.getVertexIndices().get(i) + 1)
                            .append("/").append(polygon.getTextureVertexIndices().get(i) + 1)
                            .append("/").append(polygon.getNormalIndices().get(i) + 1);
                }
            } else {
                throw new Exception("Incorrect parameters for face!");
            }
        }
        return stringBuilder.toString();
    }
}