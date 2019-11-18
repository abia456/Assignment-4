package com.example.roomdatabase;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MyDB_Impl extends MyDB {
  private volatile PlacesVisitedDoa _placesVisitedDoa;

  private volatile LocationTrackingDoa _locationTrackingDoa;

  private volatile ScreenTimeUsedDoa _screenTimeUsedDoa;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `places_visited` (`id` INTEGER NOT NULL, `place_name` TEXT, `date` TEXT, `time` TEXT, `checkin/checkout` TEXT, `customText` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LocationTracking` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `latitude` TEXT, `longitude` TEXT, `date` TEXT, `time` TEXT, `accuracy` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ScreenTimeUsed` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ScreenEevnt` TEXT, `count` INTEGER NOT NULL, `date` TEXT, `time` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '94f31876d9c371dd95d849db27b21041')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `places_visited`");
        _db.execSQL("DROP TABLE IF EXISTS `LocationTracking`");
        _db.execSQL("DROP TABLE IF EXISTS `ScreenTimeUsed`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPlacesVisited = new HashMap<String, TableInfo.Column>(6);
        _columnsPlacesVisited.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacesVisited.put("place_name", new TableInfo.Column("place_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacesVisited.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacesVisited.put("time", new TableInfo.Column("time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacesVisited.put("checkin/checkout", new TableInfo.Column("checkin/checkout", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacesVisited.put("customText", new TableInfo.Column("customText", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlacesVisited = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlacesVisited = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlacesVisited = new TableInfo("places_visited", _columnsPlacesVisited, _foreignKeysPlacesVisited, _indicesPlacesVisited);
        final TableInfo _existingPlacesVisited = TableInfo.read(_db, "places_visited");
        if (! _infoPlacesVisited.equals(_existingPlacesVisited)) {
          return new RoomOpenHelper.ValidationResult(false, "places_visited(com.example.roomdatabase.places_visited).\n"
                  + " Expected:\n" + _infoPlacesVisited + "\n"
                  + " Found:\n" + _existingPlacesVisited);
        }
        final HashMap<String, TableInfo.Column> _columnsLocationTracking = new HashMap<String, TableInfo.Column>(6);
        _columnsLocationTracking.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTracking.put("latitude", new TableInfo.Column("latitude", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTracking.put("longitude", new TableInfo.Column("longitude", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTracking.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTracking.put("time", new TableInfo.Column("time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTracking.put("accuracy", new TableInfo.Column("accuracy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLocationTracking = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLocationTracking = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLocationTracking = new TableInfo("LocationTracking", _columnsLocationTracking, _foreignKeysLocationTracking, _indicesLocationTracking);
        final TableInfo _existingLocationTracking = TableInfo.read(_db, "LocationTracking");
        if (! _infoLocationTracking.equals(_existingLocationTracking)) {
          return new RoomOpenHelper.ValidationResult(false, "LocationTracking(com.example.roomdatabase.LocationTracking).\n"
                  + " Expected:\n" + _infoLocationTracking + "\n"
                  + " Found:\n" + _existingLocationTracking);
        }
        final HashMap<String, TableInfo.Column> _columnsScreenTimeUsed = new HashMap<String, TableInfo.Column>(5);
        _columnsScreenTimeUsed.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTimeUsed.put("ScreenEevnt", new TableInfo.Column("ScreenEevnt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTimeUsed.put("count", new TableInfo.Column("count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTimeUsed.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTimeUsed.put("time", new TableInfo.Column("time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScreenTimeUsed = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScreenTimeUsed = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoScreenTimeUsed = new TableInfo("ScreenTimeUsed", _columnsScreenTimeUsed, _foreignKeysScreenTimeUsed, _indicesScreenTimeUsed);
        final TableInfo _existingScreenTimeUsed = TableInfo.read(_db, "ScreenTimeUsed");
        if (! _infoScreenTimeUsed.equals(_existingScreenTimeUsed)) {
          return new RoomOpenHelper.ValidationResult(false, "ScreenTimeUsed(com.example.roomdatabase.ScreenTimeUsed).\n"
                  + " Expected:\n" + _infoScreenTimeUsed + "\n"
                  + " Found:\n" + _existingScreenTimeUsed);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "94f31876d9c371dd95d849db27b21041", "620290f352234c6ef931f487a8120b1e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "places_visited","LocationTracking","ScreenTimeUsed");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `places_visited`");
      _db.execSQL("DELETE FROM `LocationTracking`");
      _db.execSQL("DELETE FROM `ScreenTimeUsed`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public PlacesVisitedDoa doa() {
    if (_placesVisitedDoa != null) {
      return _placesVisitedDoa;
    } else {
      synchronized(this) {
        if(_placesVisitedDoa == null) {
          _placesVisitedDoa = new PlacesVisitedDoa_Impl(this);
        }
        return _placesVisitedDoa;
      }
    }
  }

  @Override
  public LocationTrackingDoa doa2() {
    if (_locationTrackingDoa != null) {
      return _locationTrackingDoa;
    } else {
      synchronized(this) {
        if(_locationTrackingDoa == null) {
          _locationTrackingDoa = new LocationTrackingDoa_Impl(this);
        }
        return _locationTrackingDoa;
      }
    }
  }

  @Override
  public ScreenTimeUsedDoa doa3() {
    if (_screenTimeUsedDoa != null) {
      return _screenTimeUsedDoa;
    } else {
      synchronized(this) {
        if(_screenTimeUsedDoa == null) {
          _screenTimeUsedDoa = new ScreenTimeUsedDoa_Impl(this);
        }
        return _screenTimeUsedDoa;
      }
    }
  }
}
