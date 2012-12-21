package beyondthepail;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

public class PauseScreen implements Screen {

    PailGame game;
    
    public PauseScreen(PailGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.playScreen.render(delta);
        
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE)) {
            resume();
        }
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
    }

    @Override
    public void resume() {
        game.setScreen(game.playScreen);
    }

    @Override
    public void dispose() {
    }

}
