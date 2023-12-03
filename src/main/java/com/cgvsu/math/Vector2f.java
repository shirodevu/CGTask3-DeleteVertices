package com.cgvsu.math;

import static com.cgvsu.math.Constants.EPS;

public class Vector2f implements Cloneable{
    float x, y;
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector2f)) return false;
        Vector2f vector = (Vector2f) other;
        return Math.abs(x - vector.x) < EPS && Math.abs(y - vector.y) < EPS;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

	@Override
	public Vector2f clone() {
		try {
			Vector2f clone = (Vector2f) super.clone();
            clone.x = x;
            clone.y = y;
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	@Override
	public String toString() {
		return "V2F(" + x + ", " + y + ')';
	}


}