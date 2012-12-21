package beyondthepail;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class PailGame extends Game implements ApplicationListener {

    PlayScreen playScreen;
    
    @Override
	public void create() {
        playScreen = new PlayScreen(this);
        
        setScreen(playScreen);
	}

	@Override
	public void dispose() {
	    playScreen.dispose();
	}
	
}
