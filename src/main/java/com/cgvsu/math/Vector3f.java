package com.cgvsu.math;

import static com.cgvsu.math.Constants.EPS;

public class Vector3f implements Cloneable {
	float x, y, z;

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public boolean equals(Vector3f other) {
		return Math.abs(x - other.x) < EPS && Math.abs(y - other.y) < EPS && Math.abs(z - other.z) < EPS;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Vector3f)) return false;
		Vector3f vector3f = (Vector3f) obj;
		return this.equals(vector3f);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	@Override
	public Vector3f clone() {
		try {
			Vector3f clone = (Vector3f) super.clone();
			clone.x = x;
			clone.y = y;
			clone.z = z;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	@Override
	public String toString() {
		return "V3F(" + x + ", " + y + ", " + z + ')';
	}
}
