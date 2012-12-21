package beyondthepail;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PailGame implements ApplicationListener {
    static final int BUCKET_WIDTH = 48, BUCKET_HEIGHT = 48;
    
    Texture dropImage, bucketImage;
    Sound dropSound;
    Music rainMusic;
    
    OrthographicCamera camera;
    SpriteBatch batch;
    
    Rectangle bucket;
	
	@Override
	public void create() {
	    dropImage = new Texture(Gdx.files.internal("droplet.png"));
	    bucketImage = new Texture(Gdx.files.internal("bucket.png"));
	    
	    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	    
	    rainMusic.setLooping(true);
	    rainMusic.play();
	    
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    
	    batch = new SpriteBatch();
	    
	    bucket = new Rectangle();
	    bucket.width = BUCKET_WIDTH;
	    bucket.height = BUCKET_HEIGHT;
	    bucket.x = camera.viewportWidth / 2 - bucket.width / 2;
	    bucket.y = 20;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
	    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    
	    camera.update();
	    
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    batch.draw(bucketImage, bucket.x, bucket.y);
	    batch.end();
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
