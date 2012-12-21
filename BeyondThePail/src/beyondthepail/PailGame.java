package beyondthepail;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class PailGame extends Game implements ApplicationListener {

    Screen playScreen, pauseScreen, gameOverScreen;
    OrthographicCamera camera;

    @Override
	public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
        playScreen = new PlayScreen(this);
        pauseScreen = new PauseScreen(this);
        gameOverScreen = new GameOverScreen(this);
        
        setScreen(pauseScreen);
	}

	@Override
	public void dispose() {
	    playScreen.dispose();
	    pauseScreen.dispose();
	    gameOverScreen.dispose();
	}
	
}
