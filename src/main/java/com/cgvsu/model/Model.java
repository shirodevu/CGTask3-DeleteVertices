package com.cgvsu.model;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Model implements Cloneable {

    public List<Vector3f> vertices = new ArrayList<>();
    public List<Vector2f> textureVertices = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Polygon> polygons = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Arrays.deepEquals(vertices.toArray(), model.vertices.toArray())
                && Arrays.deepEquals(textureVertices.toArray(), model.textureVertices.toArray())
                && Arrays.deepEquals(normals.toArray(), model.normals.toArray())
                && Arrays.deepEquals(polygons.toArray(), model.polygons.toArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, textureVertices, normals, polygons);
    }

	@Override
	public Model clone() {
		try {
			Model clone = (Model) super.clone();
            List<Vector3f> newVertices = new ArrayList<>(vertices.stream()
		            .map(Vector3f::clone)
		            .toList());
            List<Vector3f> newNormals = new ArrayList<>(normals.stream()
		            .map(Vector3f::clone)
		            .toList());
            List<Vector2f> newTextureVertices = new ArrayList<>(textureVertices.stream()
		            .map(Vector2f::clone)
		            .toList());
            List<Polygon> newPolygons = new ArrayList<>(polygons.stream()
		            .map(Polygon::clone)
		            .toList());
			clone.textureVertices = newTextureVertices;
			clone.normals = newNormals;
			clone.vertices = newVertices;
			clone.polygons = newPolygons;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
