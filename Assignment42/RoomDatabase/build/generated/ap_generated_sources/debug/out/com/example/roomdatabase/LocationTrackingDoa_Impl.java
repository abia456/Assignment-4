package com.example.roomdatabase;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LocationTrackingDoa_Impl implements LocationTrackingDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LocationTracking> __insertionAdapterOfLocationTracking;

  public LocationTrackingDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLocationTracking = new EntityInsertionAdapter<LocationTracking>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `LocationTracking` (`id`,`latitude`,`longitude`,`date`,`time`,`accuracy`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LocationTracking value) {
        stmt.bindLong(1, value.getId());
        if (value.getLatitude() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLatitude());
        }
        if (value.getLongitude() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLongitude());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        if (value.getTime() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTime());
        }
        if (value.getAccuracy() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAccuracy());
        }
      }
    };
  }

  @Override
  public void addloacationtrack(final LocationTracking lk) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLocationTracking.insert(lk);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
