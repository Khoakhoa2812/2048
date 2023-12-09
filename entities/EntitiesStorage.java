package entities;

import gameStates.Playing;

import java.util.ArrayList;
import java.util.List;

public class EntitiesStorage {
   private static EntitiesStorage instance;

    public static EntitiesStorage createInstance(Playing playing) {
        if (instance == null) {
            instance = new EntitiesStorage(playing);
            return instance;
        } else {
            return null;
        }
    }

    private Playing playing;
    private int entitiesNo = 0;

    public EntitiesStorage(Playing playing) {
        this.playing = playing;
        tile2Store();
        tile4Store();
        tile8Store();
        tile16Store();
        tile32Store();
        tile64Store();
        tile128Store();
        tile256Store();
        tile512Store();
        tile1024Store();
        tile2048Store();
        rejectStatus();
    }

    public void tile2Store() {
        for (int i = 0; i < 500; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile2(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile4Store() {
        for (int i = 0; i < 400; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile4(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile8Store() {
        for (int i = 0; i < 300; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile8(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile16Store() {
        for (int i = 0; i < 200; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile16(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile32Store() {
        for (int i = 0; i < 100; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile32(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile64Store() {
        for (int i = 0; i < 50; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile64(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile128Store() {
        for (int i = 0; i < 24; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile128(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile256Store() {
        for (int i = 0; i < 12; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile256(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile512Store() {
        for (int i = 0; i < 6; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile512(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile1024Store() {
        for (int i = 0; i < 4; i++) {
            playing.getEntitiesManager().getEntitiesList().add(new Tile1024(playing.getBoard4x4().getTile()[0][0],entitiesNo));
            entitiesNo++;
        }
    }

    public void tile2048Store() {
        playing.getEntitiesManager().getEntitiesList().add(new Tile2048(playing.getBoard4x4().getTile()[0][0],entitiesNo));
        entitiesNo++;
    }

    public void rejectStatus() {
        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
            entities.setStatus(false);
        }
    }
}
