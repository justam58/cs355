package CS355.LWJGL;

import java.util.Iterator;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class StudentLWJGLController implements CS355LWJGLController {
	private WireFrame model = new HouseModel();
	
	//camera
	private float cameraX = 0;
	private float cameraY = -5;
	private float cameraZ = -20;
	private float cameraDirection = 0;
	private float aspect = 640 / 480;

	@Override
	public void resizeGL() {
		glViewport(0,0,640,480);
		
		// Set the aspect ratio of the clipping volume to match the viewport
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// Enable perspective projection with fovy, aspect, zNear and zFar
		gluPerspective(50.0f, aspect, 0.1f, 100f);
	}

    @Override
    public void update() {
        // no need for this lab
    }

    @Override
    public void updateKeyboard() {
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) cameraX++;
        else if(Keyboard.isKeyDown(Keyboard.KEY_D)) cameraX--;
        else if(Keyboard.isKeyDown(Keyboard.KEY_W)) cameraZ++;
        else if(Keyboard.isKeyDown(Keyboard.KEY_S)) cameraZ--;
        else if(Keyboard.isKeyDown(Keyboard.KEY_Q)) cameraDirection--;
        else if(Keyboard.isKeyDown(Keyboard.KEY_E)) cameraDirection++;
        else if(Keyboard.isKeyDown(Keyboard.KEY_R)) cameraY--;
        else if(Keyboard.isKeyDown(Keyboard.KEY_F)) cameraY++;
        else if(Keyboard.isKeyDown(Keyboard.KEY_O)) {
			// Set the aspect ratio of the clipping volume to match the viewport
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
		    // aspect >= 1, set the height from -1 to 1, with larger width
		    glOrtho(-10.0 * aspect, 10.0 * aspect, -10.0, 10.0, 0.1, 100);
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_P)) {
    		// Set the aspect ratio of the clipping volume to match the viewport
    		glMatrixMode(GL_PROJECTION);
    		glLoadIdentity();
    		// Enable perspective projection with fovy, aspect, zNear and zFar
    		gluPerspective(50.0f, aspect, 0.1f, 100f);
        }
    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        
        glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	    glRotatef(cameraDirection,0.0f,1.0f,0.0f);
	    glTranslatef(cameraX, cameraY, cameraZ);
	    
        Iterator<Line3D> linesItr = model.getLines();
        glBegin(GL_LINES);
        while(linesItr.hasNext()) {
        	Line3D line = linesItr.next();
        	glColor3f(1.0f, 1.0f, 1.0f);
        	glVertex3d(line.start.x,line.start.y,line.start.z);
        	glVertex3d(line.end.x,line.end.y,line.end.z);
        }
        glEnd();
    }
}
