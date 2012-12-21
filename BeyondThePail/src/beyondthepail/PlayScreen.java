package beyondthepail;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class PlayScreen implements Screen {
    static final int SPRITE_WIDTH = 48, SPRITE_HEIGHT = 48,
            BUCKET_SPEED = 800, RAIN_SPEED = 200;
    
    Texture spritesheetImage, legImage, sheetsImage;
    TextureRegion[][] spritesheet;
    TextureRegion dropImage, bucketImage;
    Sound dropSound;
    Music rainMusic;
    
    SpriteBatch batch;
    Camera camera;
    
    Rectangle bucket, bedLeg;
    Array<Rectangle> raindrops;
    float timeSinceLastDrop;
    long rainSpeed, // # pixels per second the rain moves at
         rainPeriod; // # ms between each raindrop
    
    PailGame game;

    public PlayScreen(PailGame game) {
        this.game = game;
        this.camera = game.camera;

        legImage = new Texture(Gdx.files.internal("leg.png"));
        sheetsImage = new Texture(Gdx.files.internal("sheets.png"));
        
        spritesheetImage = new Texture(Gdx.files.internal("sprites.png"));
        spritesheet = TextureRegion.split(
                spritesheetImage,
                SPRITE_WIDTH, SPRITE_HEIGHT);
        dropImage = spritesheet[0][1];
        bucketImage = spritesheet[0][0];
        
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        
        rainMusic.setLooping(true);
        rainMusic.play();
        
        batch = new SpriteBatch();
        
        bucket = new Rectangle();
        bucket.width = SPRITE_WIDTH;
        bucket.height = SPRITE_HEIGHT;
        bucket.x = camera.viewportWidth / 2 - bucket.width / 2;
        bucket.y = 20;
        
        bedLeg = new Rectangle();
        bedLeg.width = 122;
        bedLeg.height = camera.viewportHeight;
        bedLeg.x = camera.viewportWidth - bedLeg.width;
        bedLeg.y = 0;
        
        raindrops = new Array<Rectangle>();
        spawnRaindrop();
        rainSpeed = 200;
        rainPeriod = 1000;
    }

    @Override
    public void render(float delta) {
        
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        if (game.getScreen() == this) {
            handleInput(delta);
            updateRain(delta);
        }
        
        camera.update();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle drop: raindrops) {
            batch.draw(dropImage, drop.x, drop.y);
        }
        batch.draw(legImage, 0, 0);
        batch.draw(sheetsImage, 0, 20);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        game.setScreen(game.pauseScreen);
    }

    @Override
    public void resume() {
    }

    protected void handleInput(float delta) {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - bucket.width/2;
        }
        
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            bucket.x -= BUCKET_SPEED * delta;
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            bucket.x += BUCKET_SPEED * delta;
        
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > camera.viewportWidth - bucket.width - bedLeg.width)
            bucket.x = camera.viewportWidth - bucket.width - bedLeg.width;
        
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            game.setScreen(game.pauseScreen);
    }
    
    protected void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(
                camera.viewportWidth - SPRITE_WIDTH - bedLeg.width);
        raindrop.y = camera.viewportHeight;
        raindrop.width = SPRITE_WIDTH;
        raindrop.height = SPRITE_HEIGHT;
        raindrops.add(raindrop);
        ++rainSpeed;
        --rainPeriod;
        timeSinceLastDrop = 0.0f;
    }
    
    protected void updateRain(float delta) {
        timeSinceLastDrop += delta;
        if (timeSinceLastDrop*1000 > rainPeriod) spawnRaindrop();
        
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= rainSpeed * delta;
            if (raindrop.y + raindrop.height < 0) iter.remove();
            
            if (raindrop.overlaps(bucket)) {
                dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void dispose() {
        spritesheetImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
    }

}
