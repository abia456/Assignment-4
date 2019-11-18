package com.example.roomdatabase;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ScreenTimeUsedDoa_Impl implements ScreenTimeUsedDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ScreenTimeUsed> __insertionAdapterOfScreenTimeUsed;

  public ScreenTimeUsedDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScreenTimeUsed = new EntityInsertionAdapter<ScreenTimeUsed>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ScreenTimeUsed` (`id`,`ScreenEevnt`,`count`,`date`,`time`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ScreenTimeUsed value) {
        stmt.bindLong(1, value.getId());
        if (value.getScreenEevnt() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getScreenEevnt());
        }
        stmt.bindLong(3, value.getCount());
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
      }
    };
  }

  @Override
  public void addScreenTime(final ScreenTimeUsed st) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfScreenTimeUsed.insert(st);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
