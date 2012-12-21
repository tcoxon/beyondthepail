package beyondthepail;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class PailGame extends Game implements ApplicationListener {

    Screen playScreen, pauseScreen;
    
    @Override
	public void create() {
        playScreen = new PlayScreen(this);
        pauseScreen = new PauseScreen(this);
        
        setScreen(pauseScreen);
	}

	@Override
	public void dispose() {
	    playScreen.dispose();
	    pauseScreen.dispose();
	}
	
}
