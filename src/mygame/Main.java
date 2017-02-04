package mygame;

import Terrain.DanGenius;
import Terrain.HillGenerator;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.FaultHeightMap;
import com.jme3.terrain.heightmap.HillHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.terrain.heightmap.MidpointDisplacementHeightMap;
import com.jme3.terrain.heightmap.ParticleDepositionHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    private TerrainQuad terrain;
    Material mat_terrain;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
           flyCam.setMoveSpeed(250);

        Material terrainMat = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");
        terrainMat.setBoolean("useTriPlanarMapping", false);

        terrainMat.setTexture("Alpha", assetManager.loadTexture("Textures/Terrain/alphamap.png"));
        // Grass texture for the splatting material
        Texture grass = assetManager.loadTexture("Textures/Terrain/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        terrainMat.setTexture("Tex1", grass);
        terrainMat.setFloat("Tex1Scale", 32);
        // Rock texture for the splatting material
        Texture rock = assetManager.loadTexture("Textures/Terrain/grass.jpg");
        rock.setWrap(WrapMode.Repeat);
        terrainMat.setTexture("Tex2", rock);
        terrainMat.setFloat("Tex2Scale", 32);
        // Road texture for the splatting material
        Texture road = assetManager.loadTexture("Textures/Terrain/grass.jpg");
        road.setWrap(WrapMode.Repeat);
        terrainMat.setTexture("Tex3", road);
        terrainMat.setFloat("Tex3Scale", 32);
        Texture heightMapImage = assetManager.loadTexture("Textures/Terrain/Height.png" );
        AbstractHeightMap heightmap = null;
        try {
        //heightmap = new ImageBasedHeightMap(heightMapImage.getImage(), 0.5f );
        
        ArrayList<Vector3f> list = new ArrayList<>();
      //  list.add(new Vector3f(50,50,10));
        //list.add(new Vector3f(300, 300,3));
        //list.add(new Vector3f(123, 12, 1));
        //list.add(new Vector3f(512, 512,2));
        //list.add(new Vector3f(700, 700, 4));
        
        list.add(new Vector3f(300, 300, 6000));
     //   list.add(new Vector3f(250, 1024, 100));
        list.add(new Vector3f(500, 500, 4000));
        /*list.add(new Vector3f(56, 98,10));
        list.add(new Vector3f(500, 500,3));
        list.add(new Vector3f(400, 29,12));*/
        heightmap = new HillGenerator(2049, list, 9, 30, 3);
        //heightmap = new FaultHeightMap(512, 1, FaultHeightMap.FAULTTYPE_COSINE, FaultHeightMap.FAULTSHAPE_CIRCLE, 10, 100, 5);
        //heightmap = new DanGenius(513, 5000000, 1, 9,3,100);
        } catch (Exception e) { e.printStackTrace(); }
        float[] map = new float[262150];
        for(int i = 0; i<262144; i++)
        {
            int m = i % 512;
            map[i] = (float)m;
        }
        TerrainQuad terrain = new TerrainQuad("terrain", 65, 1025, heightmap.getHeightMap());
         List<Camera> cameras = new ArrayList<Camera>();
        cameras.add(getCamera());
    //    terrain.setLocalScale(new Vector3f(5, 5, 5));
        terrain.setLocalTranslation(new Vector3f(0, -30, 0));
        terrain.setLocked(false); // unlock it so we can edit the height

        terrain.setShadowMode(RenderQueue.ShadowMode.Receive);
        terrain.setMaterial(terrainMat);
        rootNode.attachChild(terrain);
    
        cam.setLocation(new Vector3f(0, 512, 512));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);     
    
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
