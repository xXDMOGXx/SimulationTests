package com.xxdmogxx.core.render.buffers;

import com.xxdmogxx.core.utils.Utils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;

public class VBO {

    private final int ID;
    private final FloatBuffer buffer;

    public VBO(float[] data) {
        // Generate a VBO and get its reference id
        ID = GL15.glGenBuffers();
        // Bind the VBO specifying it's a GL_ARRAY_BUFFER
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ID);
        // Store the array of vertices into a float buffer in memory
        buffer = Utils.storeDataInFloatBuffer(data);
        // Introduce the float buffer into the VBO
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
    }

    public void update(float[] data) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ID);
        Utils.updateDataInFloatBuffer(buffer, data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
    }

    public void bind() {
        // Bind the VBO specifying it's a GL_ARRAY_BUFFER
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ID);
    }

    public void unbind() {
        // Bind the VBO to 0 (No Buffer) so that we don't accidentally modify the VBO we created
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public void delete() {
        GL30.glDeleteBuffers(ID);
    }

    public int getID() {
        return ID;
    }
}
