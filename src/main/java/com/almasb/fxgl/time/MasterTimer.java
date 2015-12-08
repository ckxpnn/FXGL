/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.fxgl.time;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.util.Duration;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public interface MasterTimer {

    long getNow();

    default long getTick() {
        return tickProperty().get();
    }

    void resetTicks();

    ReadOnlyLongProperty tickProperty();

    /**
     * @return average render FPS
     */
    default int getFPS() {
        return fpsProperty().get();
    }

    /**
     * @return average render FPS property
     */
    IntegerProperty fpsProperty();

    /**
     * @return Average performance FPS
     */
    default int getPerformanceFPS() {
        return performanceFPSProperty().get();
    }

    /**
     * @return Average performance FPS property
     */
    IntegerProperty performanceFPSProperty();

    /**
     * Called at the start of a game update tick.
     * This is where tick becomes tick + 1.
     *
     * @param internalTime internal JavaFX time
     */
    void tickStart(long internalTime);

    /**
     * Called at the end of a game update tick.
     */
    void tickEnd();

    /**
     * The Runnable action will be scheduled to run at given interval.
     * The action will run for the first time after given interval.
     * <p>
     * Note: the scheduled action will not run while the game is paused.
     *
     * @param action   the action
     * @param interval time
     */
    void runAtInterval(Runnable action, Duration interval);

    /**
     * The Runnable action will be scheduled for execution iff
     * whileCondition is initially true. If that's the case
     * then the Runnable action will be scheduled to run at given interval.
     * The action will run for the first time after given interval
     * <p>
     * The action will be removed from schedule when whileCondition becomes {@code false}.
     * <p>
     * Note: the scheduled action will not run while the game is paused
     *
     * @param action         action to execute
     * @param interval       interval between executions
     * @param whileCondition condition
     */
    void runAtIntervalWhile(Runnable action, Duration interval, ReadOnlyBooleanProperty whileCondition);

    /**
     * The Runnable action will be executed once after given delay
     * <p>
     * Note: the scheduled action will not run while the game is paused
     *
     * @param action action to execute
     * @param delay  delay after which to execute
     */
    void runOnceAfter(Runnable action, Duration delay);

    /**
     * Clears all registered timer based actions.
     */
    void clearActions();
}
