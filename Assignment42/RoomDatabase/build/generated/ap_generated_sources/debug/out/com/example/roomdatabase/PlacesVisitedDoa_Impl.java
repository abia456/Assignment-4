package com.example.roomdatabase;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlacesVisitedDoa_Impl implements PlacesVisitedDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<places_visited> __insertionAdapterOfplaces_visited;

  public PlacesVisitedDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfplaces_visited = new EntityInsertionAdapter<places_visited>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `places_visited` (`id`,`place_name`,`date`,`time`,`checkin/checkout`,`customText`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, places_visited value) {
        stmt.bindLong(1, value.getId());
        if (value.getPlace_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlace_name());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        if (value.getTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTime());
        }
        if (value.getCheckinorout() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCheckinorout());
        }
        if (value.getCustomText() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCustomText());
        }
      }
    };
  }

  @Override
  public void addPlace(final places_visited pv) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfplaces_visited.insert(pv);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
