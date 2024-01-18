-- Table: users
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Table: spirits
CREATE TABLE IF NOT EXISTS spirits (
    id SERIAL PRIMARY KEY NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL UNIQUE,
    image VARCHAR(255) NOT NULL
);

-- Table: adversaries
CREATE TABLE IF NOT EXISTS adversaries (
    id SERIAL PRIMARY KEY NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL UNIQUE,
    flag VARCHAR(255) NOT NULL
);

-- Table: game_sessions
CREATE TABLE IF NOT EXISTS game_sessions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES "user"(id),
    spirit_id INTEGER NOT NULL REFERENCES spirits(id),
    adversary_id INTEGER NOT NULL REFERENCES adversaries(id),
    board VARCHAR(255) NOT NULL,
    session_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    played_on DATE DEFAULT CURRENT_DATE,
    result VARCHAR(255),
    is_completed BOOLEAN
);

-- Foreign key relationships
ALTER TABLE game_sessions
    ADD CONSTRAINT fk_user_id
    FOREIGN KEY (user_id)
    REFERENCES users(id);

ALTER TABLE game_sessions
    ADD CONSTRAINT fk_spirit_id
    FOREIGN KEY (spirit_id)
    REFERENCES spirits(id);

ALTER TABLE game_sessions
    ADD CONSTRAINT fk_adversary_id
    FOREIGN KEY (adversary_id)
    REFERENCES adversaries(id);

-- TEST VALUES
INSERT INTO users(id, email, username, password) VALUES
    (1, 'email@email.com', 'username', 'password');

INSERT INTO spirits(id, name, image) VALUES
    (1, 'Lightning''s Swift Strike', 'https://spiritislandwiki.com/images/c/c2/Lightning%27s_Swift_Strike.png'),
    (2, 'River Surges in Sunlight', 'https://spiritislandwiki.com/images/f/ff/River_Surges_in_Sunlight.png'),
    (3, 'Shadows Flicker Like Flame', 'https://spiritislandwiki.com/images/d/d2/Shadows_Flicker_Like_Flame.png'),
    (4, 'Vital Strength of the Earth', 'https://spiritislandwiki.com/images/c/c2/Vital_Strength_of_the_Earth.png'),
    (5, 'A Spread of Rampant Green', 'https://spiritislandwiki.com/images/7/7f/A_Spread_of_Rampant_Green.png'),
    (6, 'Thunderspeaker', 'https://spiritislandwiki.com/images/b/bd/Thunderspeaker.png'),
    (7, 'Bringer of Dreams and Nightmares', 'https://spiritislandwiki.com/images/a/aa/Bringer_of_Dreams_and_Nightmares.png'),
    (8, 'Ocean''s Hungry Grasp', 'https://spiritislandwiki.com/images/d/d8/Ocean%27s_Hungry_Grasp.png'),
    (9, 'Keeper of the Forbidden Wilds', 'https://spiritislandwiki.com/images/a/ae/Keeper_of_the_Forbidden_Wilds.png'),
    (10, 'Sharp Fangs Behind the Leaves', 'https://spiritislandwiki.com/images/a/a5/Sharp_Fangs_Behind_the_Leaves.png'),
    (11, 'Heart of the Wildfire', 'https://spiritislandwiki.com/images/2/21/Heart_of_the_Wildfire.png'),
    (12, 'Serpent Slumbering Beneath the Island', 'https://spiritislandwiki.com/images/1/1a/Serpent_Slumbering_Beneath_the_Island.png'),
    (13, 'Grinning Trickster Stirs Up Trouble', 'https://spiritislandwiki.com/images/f/f0/Grinning_Trickster_Stirs_Up_Trouble.png'),
    (14, 'Lure of the Deep Wilderness', 'https://spiritislandwiki.com/images/2/25/Lure_of_the_Deep_Wilderness.png'),
    (15, 'Many Minds Move as One', 'https://spiritislandwiki.com/images/a/a3/Many_Minds_Move_as_One.png'),
    (16, 'Shifting Memory of Ages', 'https://spiritislandwiki.com/images/0/0f/Shifting_Memory_of_Ages.png'),
    (17, 'Stone''s Unyielding Defiance', 'https://spiritislandwiki.com/images/b/bf/Stone%27s_Unyielding_Defiance.png'),
    (18, 'Volcano Looming High', 'https://spiritislandwiki.com/images/3/33/Volcano_Looming_High.png'),
    (19, 'Shroud of Silent Mist', 'https://spiritislandwiki.com/images/4/4e/Shroud_of_Silent_Mist.png'),
    (20, 'Vengeance as a Burning Plague', 'https://spiritislandwiki.com/images/f/f2/Vengeance_as_a_Burning_Plague.png'),
    (21, 'Fractured Days Split the Sky', 'https://spiritislandwiki.com/images/8/81/Fractured_Days_Split_the_Sky.png'),
    (22, 'Starlight Seeks Its Form', 'https://spiritislandwiki.com/index.php?title=Starlight_Seeks_Its_Form'),
    (23, 'Downpour Drenches the World', 'https://spiritislandwiki.com/images/7/74/Downpour_Drenches_the_World.png'),
    (24, 'Finder of Paths Unseen', 'https://spiritislandwiki.com/images/d/d9/Finder_of_Paths_Unseen.png'),
    (25, 'Devouring Teeth Lurk Underfoot', 'https://spiritislandwiki.com/images/d/dc/Devouring_Teeth_Lurk_Underfoot.png'),
    (26, 'Eyes Watch from the Trees', 'https://spiritislandwiki.com/images/5/53/Eyes_Watch_from_the_Trees.png'),
    (27, 'Fathomless Mud of the Swamp', 'https://spiritislandwiki.com/images/f/f6/Fathomless_Mud_of_the_Swamp.png'),
    (28, 'Rising Heat of Stone and Sand', 'https://spiritislandwiki.com/images/7/7f/Rising_Heat_of_Stone_and_Sand.png'),
    (29, 'Sun-Bright Whirlwind', 'https://spiritislandwiki.com/images/7/7b/Sun-Bright_Whirlwind.png'),
    (30, 'Ember-Eyed Behemoth', 'https://spiritislandwiki.com/images/4/4e/Ember-Eyed_Behemoth.png'),
    (31, 'Towering Roots of the Jungle', 'https://spiritislandwiki.com/images/7/74/Towering_Roots_of_the_Jungle.png'),
    (32, 'Hearth Vigil', 'https://spiritislandwiki.com/images/c/c0/Hearth-Vigil.png'),
    (33, 'Breath of Darkness Down Your Spine', 'https://spiritislandwiki.com/images/0/03/Breath_of_Darkness_Down_Your_Spine.png'),
    (34, 'Relentless Gaze of the Sun', 'https://spiritislandwiki.com/images/0/04/Relentless_Gaze_of_the_Sun.png'),
    (35, 'Wandering Voice Keens Delirium', 'https://spiritislandwiki.com/images/a/a4/Wandering_Voice_Keens_Delirium.png'),
    (36, 'Wounded Waters Bleeding', 'https://spiritislandwiki.com/images/2/24/Wounded_Waters_Bleeding.png'),
    (37, 'Dances Up Earthquakes', 'https://spiritislandwiki.com/images/4/4c/Dances_Up_Earthquakes.png');

INSERT INTO adversaries(id, name, flag) VALUES
    (1, 'Brandenburg-Prussia', 'https://spiritislandwiki.com/images/d/de/Brand-Prussia_Flag.png'),
    (2, 'England', 'https://spiritislandwiki.com/images/2/2e/England_WrinkledFlag.png'),
    (3, 'Sweden', 'https://spiritislandwiki.com/images/4/4a/Sweden_WrinkledFlag.png'),
    (4, 'France (Plantation Colony)', 'https://spiritislandwiki.com/images/b/bd/France_%28Plantation_Colony%29_WrinkledFlag.png'),
    (5, 'Habsburg Monarchy (Livestock Colony)', 'https://spiritislandwiki.com/images/7/71/Habsburg_Monarchy_%28Livestock_Colony%29_WrinkledFlag.png'),
    (6, 'Russia', 'https://spiritislandwiki.com/images/e/e9/Russia_WrinkledFlag.png'),
    (7, 'Scotland', 'https://spiritislandwiki.com/images/9/9f/Scotland_WrinkledFlag.png'),
    (8, 'Habsburg Mining Expedition', 'https://spiritislandwiki.com/images/1/13/Habsburg_Mining_Expedition_Flag.png');

INSERT INTO game_sessions(id, user_id, spirit_id, adversary_id, board, session_name, description, played_on, result, is_completed) VALUES
    (1, 1, 1, 1, 'A', 'My First Game', 'This is my first game of Spirit Island!', '2023-12-20', 'Win', true);