package beyondthepail;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class PailGame implements ApplicationListener {
    static final int SPRITE_WIDTH = 48, SPRITE_HEIGHT = 48,
            BUCKET_SPEED = 800;
    
    TextureRegion[][] spritesheet;
    TextureRegion dropImage, bucketImage;
    Sound dropSound;
    Music rainMusic;
    
    OrthographicCamera camera;
    SpriteBatch batch;
    
    Rectangle bucket;
	
	@Override
	public void create() {
	    spritesheet = TextureRegion.split(
	            new Texture(Gdx.files.internal("sprites.png")),
	            SPRITE_WIDTH, SPRITE_HEIGHT);
	    dropImage = spritesheet[0][1];
	    bucketImage = spritesheet[0][0];
	    
	    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	    
	    rainMusic.setLooping(true);
	    rainMusic.play();
	    
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    
	    batch = new SpriteBatch();
	    
	    bucket = new Rectangle();
	    bucket.width = SPRITE_WIDTH;
	    bucket.height = SPRITE_HEIGHT;
	    bucket.x = camera.viewportWidth / 2 - bucket.width / 2;
	    bucket.y = 20;
	}

	@Override
	public void dispose() {
	}
	
	protected void handleInput() {
	    if (Gdx.input.isTouched()) {
	        Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        camera.unproject(touchPos);
	        bucket.x = touchPos.x - bucket.width/2;
	    }
	    
	    if (Gdx.input.isKeyPressed(Keys.LEFT))
	        bucket.x -= BUCKET_SPEED * Gdx.graphics.getDeltaTime();
	    if (Gdx.input.isKeyPressed(Keys.RIGHT))
	        bucket.x += BUCKET_SPEED * Gdx.graphics.getDeltaTime();
	    
	    if (bucket.x < 0) bucket.x = 0;
	    if (bucket.x > camera.viewportWidth - bucket.width)
	        bucket.x = camera.viewportWidth - bucket.width;
	    
	}

	@Override
	public void render() {
	    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    
	    handleInput();
	    
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
