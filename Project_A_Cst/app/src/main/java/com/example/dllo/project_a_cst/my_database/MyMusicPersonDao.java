package com.example.dllo.project_a_cst.my_database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MY_MUSIC_PERSON".
*/
public class MyMusicPersonDao extends AbstractDao<MyMusicPerson, Long> {

    public static final String TABLENAME = "MY_MUSIC_PERSON";

    /**
     * Properties of entity MyMusicPerson.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MusicId = new Property(1, long.class, "musicId", false, "MUSIC_ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Artist = new Property(3, String.class, "artist", false, "ARTIST");
        public final static Property AlbumId = new Property(4, long.class, "albumId", false, "ALBUM_ID");
        public final static Property Url = new Property(5, String.class, "url", false, "URL");
    }


    public MyMusicPersonDao(DaoConfig config) {
        super(config);
    }
    
    public MyMusicPersonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MY_MUSIC_PERSON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"MUSIC_ID\" INTEGER NOT NULL ," + // 1: musicId
                "\"TITLE\" TEXT," + // 2: title
                "\"ARTIST\" TEXT," + // 3: artist
                "\"ALBUM_ID\" INTEGER NOT NULL ," + // 4: albumId
                "\"URL\" TEXT);"); // 5: url
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MY_MUSIC_PERSON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MyMusicPerson entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMusicId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(4, artist);
        }
        stmt.bindLong(5, entity.getAlbumId());
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MyMusicPerson entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMusicId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(4, artist);
        }
        stmt.bindLong(5, entity.getAlbumId());
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MyMusicPerson readEntity(Cursor cursor, int offset) {
        MyMusicPerson entity = new MyMusicPerson( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // musicId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // artist
            cursor.getLong(offset + 4), // albumId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // url
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MyMusicPerson entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMusicId(cursor.getLong(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setArtist(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAlbumId(cursor.getLong(offset + 4));
        entity.setUrl(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MyMusicPerson entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MyMusicPerson entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MyMusicPerson entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
