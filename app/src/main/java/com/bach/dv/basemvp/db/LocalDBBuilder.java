package com.bach.dv.basemvp.db;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bach.dv.basemvp.App;
import com.bach.dv.basemvp.ui.main.MainActivity;

public class LocalDBBuilder {

    static final Migration MIGRATION_10_11 = new Migration(10, 11) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //TableInfo{name='tblHistoryStory', columns={modifiedDate=Column{name='modifiedDate', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0}, albumId=Column{name='albumId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, historyStoryId=Column{name='historyStoryId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1}, history=Column{name='history', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, createdDate=Column{name='createdDate', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0}}, foreignKeys=[], indices=[]}
            database.execSQL("CREATE TABLE " + DBConstant.HISTORY_STORY_TABLE_NAME + " (`historyStoryId` INTEGER NOT NULL PRIMARY KEY, `history` TEXT , `albumId` INTEGER NOT NULL, `modifiedDate` INTEGER, `createdDate` INTEGER)");
        }
    };

    static final Migration MIGRATION_11_12 = new Migration(11, 12) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //TableInfo{name='tblHistoryStory', columns={modifiedDate=Column{name='modifiedDate', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0}, albumId=Column{name='albumId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, historyStoryId=Column{name='historyStoryId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1}, history=Column{name='history', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, createdDate=Column{name='createdDate', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0}}, foreignKeys=[], indices=[]}
            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN fullUrl TEXT");
        }
    };

    static final Migration MIGRATION_12_13 = new Migration(12, 13) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //TableInfo{name='tblHistoryStory', columns={modifiedDate=Column{name='modifiedDate', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0}, albumId=Column{name='albumId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, historyStoryId=Column{name='historyStoryId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1}, history=Column{name='history', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, createdDate=Column{name='createdDate', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0}}, foreignKeys=[], indices=[]}
            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_STATUS_DOWNLOAD + " INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_DATETIME_DOWNLOAD + " INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_ALBUM + " TEXT");
            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_DETAIL + " TEXT");
//            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_DURATION + " INTEGER");
//            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_BOOKMARKED + " INTEGER");
//            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_LIKED + " INTEGER");
//            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_NUMBER_VIEW + " INTEGER");
//            database.execSQL("ALTER TABLE " + DBConstant.PODCAST_TABLE_NAME + " ADD COLUMN " + DBConstant.PODCAST_ALBUM_THUMB + " TEXT");
        }
    };

    public static LocalDB getInstance() {
        LocalDB localDB = Room.databaseBuilder(App.getAppContext(), LocalDB.class, DBConstant.DB_NAME).allowMainThreadQueries()
                .addMigrations(MIGRATION_10_11, MIGRATION_11_12, MIGRATION_12_13).build();
        return localDB;
    }

}
