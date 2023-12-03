package com.cgvsu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Polygon implements Cloneable{

    private List<Integer> vertexIndices;
    private List<Integer> textureVertexIndices;
    private List<Integer> normalIndices;


    public Polygon() {
        vertexIndices = new ArrayList<>();
        textureVertexIndices = new ArrayList<>();
        normalIndices = new ArrayList<>();
    }

    public void setVertexIndices(List<Integer> vertexIndices) {
        //assert vertexIndices.size() >= 3;
        this.vertexIndices = vertexIndices;
    }

    public void setVertexIndices(int... vertexIndices) {
        //assert vertexIndices.size() >= 3;
        this.vertexIndices = new ArrayList<>();
        for (int vertexIndex : vertexIndices) {
            this.vertexIndices.add(vertexIndex);
        }
    }

    public void setTextureVertexIndices(List<Integer> textureVertexIndices) {
        //assert textureVertexIndices.size() >= 3;
        this.textureVertexIndices = textureVertexIndices;
    }

    public void setTextureVertexIndices(int... textureVertexIndices) {
        //assert textureVertexIndices.size() >= 3;
        this.textureVertexIndices = new ArrayList<>();
        for (int textureVertexIndex : textureVertexIndices) {
            this.textureVertexIndices.add(textureVertexIndex);
        }
    }

    public void setNormalIndices(List<Integer> normalIndices) {
        //assert normalIndices.size() >= 3;
        this.normalIndices = normalIndices;
    }

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public List<Integer> getTextureVertexIndices() {
        return textureVertexIndices;
    }

    public List<Integer> getNormalIndices() {
        return normalIndices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return Arrays.deepEquals(getVertexIndices().toArray(), polygon.getVertexIndices().toArray())
                && Arrays.deepEquals(getTextureVertexIndices().toArray(), polygon.getTextureVertexIndices().toArray())
                && Arrays.deepEquals(getNormalIndices().toArray(), polygon.getNormalIndices().toArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVertexIndices(), getTextureVertexIndices(), getNormalIndices());
    }

	@Override
	public Polygon clone() {
		try {
			Polygon clone = (Polygon) super.clone();
            clone.normalIndices = new ArrayList<>(normalIndices.stream().map(it -> Integer.valueOf(it.intValue())).toList());
            clone.vertexIndices = new ArrayList<>(vertexIndices.stream().map(it -> Integer.valueOf(it.intValue())).toList());
            clone.textureVertexIndices = new ArrayList<>(textureVertexIndices.stream().map(it -> Integer.valueOf(it.intValue())).toList());
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Polygon {").append('\n');
        sb.append("v=").append(vertexIndices).append('\n');
        sb.append("vt=").append(textureVertexIndices).append('\n');
        sb.append("vn=").append(normalIndices).append('\n');
        sb.append('}');
        return sb.toString();
    }
}
