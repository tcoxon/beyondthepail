package beyondthepail;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class PailGame implements ApplicationListener {
    Texture dropImage, bucketImage;
    Sound dropSound;
    Music rainMusic;
	
	@Override
	public void create() {
	    dropImage = new Texture(Gdx.files.internal("droplet.png"));
	    bucketImage = new Texture(Gdx.files.internal("bucket.png"));
	    
	    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	    
	    rainMusic.setLooping(true);
	    rainMusic.play();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
