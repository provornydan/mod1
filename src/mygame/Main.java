package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.HillHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

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
        terrainMat.setTexture("Alpha", assetManager.loadTexture("Textures/Terrain/alphamap.png"));
        // Grass texture for the splatting material
        Texture grass = assetManager.loadTexture("Textures/Terrain/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        terrainMat.setTexture("Tex1", grass);
        terrainMat.setFloat("Tex1Scale", 32);
        // Rock texture for the splatting material
        Texture rock = assetManager.loadTexture("Textures/Terrain/rock.jpg");
        rock.setWrap(WrapMode.Repeat);
        terrainMat.setTexture("Tex2", rock);
        terrainMat.setFloat("Tex2Scale", 64);
        // Road texture for the splatting material
        Texture road = assetManager.loadTexture("Textures/Terrain/road.jpg");
        road.setWrap(WrapMode.Repeat);
        terrainMat.setTexture("Tex3", road);
        terrainMat.setFloat("Tex3Scale", 16);
        
        Texture heightMapImage = assetManager.loadTexture("Textures/Terrain/Height.png" );
        AbstractHeightMap heightmap = null;
        try {
        //heightmap = new ImageBasedHeightMap(heightMapImage.getImage(), 0.5f );
        heightmap = new HillHeightMap(1025, 500, 50, 100);
        heightmap.load();
        } catch (Exception e) { e.printStackTrace(); }

        TerrainQuad terrain = new TerrainQuad("terrain", 65, 513, heightmap.getHeightMap() );
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
