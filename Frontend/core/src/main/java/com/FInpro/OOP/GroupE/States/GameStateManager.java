package com.FInpro.OOP.GroupE.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;


public class GameStateManager {

    // The Stack that holds our active states (LIFO - Last In First Out)
    private final Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<GameState>();
    }

    public void push(GameState state) {
        states.push(state);
    }

    public void pop() {
        GameState s = states.pop();
        s.dispose(); // Always clean up memory when removing a state
    }

    public void set(GameState state) {
        if (!states.isEmpty()) {
            pop();
        }
        push(state);
    }


    public void update(float dt) {
        if (!states.isEmpty()) {
            states.peek().update(dt);
        }
    }

    /**
     * Render the state currently on top of the stack
     */
    public void render(SpriteBatch sb) {
        if (!states.isEmpty()) {
            states.peek().render(sb);
        }
    }
}
