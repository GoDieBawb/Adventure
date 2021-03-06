package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        Node n = (Node) assetManager.loadModel("Scenes/TestScene.j3o");
        rootNode.attachChild(n);
        AmbientLight al = new AmbientLight();
        //rootNode.addLight(al);
        makeUnshaded(n);
        flyCam.setMoveSpeed(5);
        
    }
    
    public void makeUnshaded(Node node) {
      
        SceneGraphVisitor sgv = new SceneGraphVisitor() {
 
            public void visit(Spatial spatial) {
        
                    if (spatial instanceof Geometry) {
          
                    Geometry geom = (Geometry) spatial;
                    Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                    Material tat = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");

                    if (geom.getName().equals("Invisible")) {
            
                    }
        
                    else if (geom.getMaterial().getTextureParam("DiffuseMap_1") != null) {
            
                        tat.setTexture("Alpha", geom.getMaterial().getTextureParam("AlphaMap").getTextureValue());
          
                        if (geom.getMaterial().getTextureParam("DiffuseMap") != null) {
           
                            tat.setTexture("Tex1", geom.getMaterial().getTextureParam("DiffuseMap").getTextureValue());
                            tat.getTextureParam("Tex1").getTextureValue().setWrap(Texture.WrapMode.Repeat);
                            tat.setFloat("Tex1Scale", Float.valueOf(geom.getMaterial().getParam("DiffuseMap_0_scale").getValueAsString()));
          
                        }
        
                        if (geom.getMaterial().getTextureParam("DiffuseMap_1") != null) {
              
                            tat.setTexture("Tex2", geom.getMaterial().getTextureParam("DiffuseMap_1").getTextureValue());
                            tat.getTextureParam("Tex2").getTextureValue().setWrap(Texture.WrapMode.Repeat);
                            tat.setFloat("Tex2Scale", Float.valueOf(geom.getMaterial().getParam("DiffuseMap_1_scale").getValueAsString()));
          
                        }
        
                        if (geom.getMaterial().getTextureParam("DiffuseMap_2") != null) {
              
                            tat.setTexture("Tex3", geom.getMaterial().getTextureParam("DiffuseMap_2").getTextureValue());
                            tat.getTextureParam("Tex3").getTextureValue().setWrap(Texture.WrapMode.Repeat);
                            tat.setFloat("Tex3Scale", Float.valueOf(geom.getMaterial().getParam("DiffuseMap_2_scale").getValueAsString()));
          
                        }

                        geom.setMaterial(tat);
          
                    }
        
                    else if (geom.getMaterial().getTextureParam("DiffuseMap") != null) {
              
                        mat.setTexture("ColorMap", geom.getMaterial().getTextureParam("DiffuseMap").getTextureValue());
                        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
                        geom.setMaterial(mat);
              
                    }
       
                }
      
            }
    
        };
    
    node.depthFirstTraversal(sgv);
    
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
