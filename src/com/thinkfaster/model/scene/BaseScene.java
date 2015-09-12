package com.thinkfaster.model.scene;

import android.app.Activity;
import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.util.SceneType;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * User: Breku
 * Date: 21.09.13
 */
public abstract class BaseScene extends Scene {

    protected Engine engine;
    protected Activity activity;
    protected ResourcesManager resourcesManager;
    protected VertexBufferObjectManager vertexBufferObjectManager;
    protected Camera camera;

    public BaseScene(Object... objects) {
        this.resourcesManager = ResourcesManager.getInstance();
        this.engine = resourcesManager.getEngine();
        this.activity = resourcesManager.getActivity();
        this.vertexBufferObjectManager = resourcesManager.getVertexBufferObjectManager();
        this.camera = resourcesManager.getCamera();
        initializeServices();
        createScene(objects);
    }

    public void initializeServices(){
        // intentionally left blank
    }

    public abstract void createScene(Object... objects);

    public abstract void onBackKeyPressed();

    public abstract SceneType getSceneType();

    public abstract void disposeScene();
}
