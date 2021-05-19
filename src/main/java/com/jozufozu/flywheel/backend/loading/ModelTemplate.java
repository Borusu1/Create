package com.jozufozu.flywheel.backend.loading;

import com.jozufozu.flywheel.Flywheel;
import com.jozufozu.flywheel.backend.ShaderLoader;
import com.jozufozu.flywheel.backend.gl.shader.ShaderType;

import net.minecraft.util.ResourceLocation;

public class ModelTemplate extends ProgramTemplate {
	public static final String vertexData = "VertexData";
	public static final String fragment = "Fragment";

	public static final String vertexPrefix = "a_v_";

	public static final String[] requiredVert = new String[]{vertexData, fragment};

	public static final String[] requiredFrag = {fragment};

	public static final ResourceLocation vert = new ResourceLocation(Flywheel.ID, "template/model/model.vert");
	public static final ResourceLocation frag = new ResourceLocation(Flywheel.ID, "template/model/model.frag");

	public ModelTemplate(ShaderLoader loader) {
		super(loader);

		templates.put(ShaderType.VERTEX, new ShaderTemplate(requiredVert, loader.getShaderSource(vert)));
		templates.put(ShaderType.FRAGMENT, new ShaderTemplate(requiredFrag, loader.getShaderSource(frag)));
	}

	@Override
	public void attachAttributes(Program builder) {
		Shader shader = builder.attached.get(ShaderType.VERTEX);

		shader.getTag(vertexData).addPrefixedAttributes(builder, vertexPrefix);
	}
}
