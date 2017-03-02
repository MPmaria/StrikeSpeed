package com.mp.user.strikespeed.database;

import com.mp.user.strikespeed.entity.Strike;

public class DbSchema {
    public static final class StrikeTable {
        public static final String NAME = "strikes";

        public static final class Cols {
            public static final String TYPE = "type";
            public static final String IS_RIGHT = "is_right";
            public static final String HAS_GLOVE = "has_glove";
            public static final String HAS_STEP = "has_step";
            public static final String STRIKE_SPEED = "strike_speed";
            public static final String REACTION_SPEED = "reaction_time";
            public static final String ACCELERATION = "acceleration";
            public static final String DATE = "date";
        }
    }
}