package com.cj.kingscup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple database access helper class. Defines the basic CRUD operations for
 * the card database
 */
public class KingsCupDbAdapter {
	// Columns in the cards table
	public static final String KEY_ROWID = "_id";
	public static final String KEY_RDRAWABLE = "rdrawable";
	public static final String KEY_CARDNAME = "cardname";
	public static final String KEY_RULE = "rule";

	public static final String TABLE_NAME = "cards";

	private static final String TAG = "KingsCupDbAdapter";
	private DatabaseHelper myDatabaseHelper;
	private SQLiteDatabase myDatabase;

	/**
	 * Database creation sql statement
	 */
	private static final String DATABASE_CREATE = "create table " + TABLE_NAME
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_RDRAWABLE + " int not null, " + KEY_CARDNAME
			+ " text not null, " + KEY_RULE + " text not null);";

	private static final String DATABASE_NAME = "kingscup";
	private static final int DATABASE_VERSION = 1;

	private final Context myContext;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}

	/**
	 * Requries the context as a parameter so the database can be opened.
	 * 
	 * @param context
	 *            the Context within which to work
	 */
	public KingsCupDbAdapter(Context context) {
		this.myContext = context;
	}

	/**
	 * Opens the database unless the database hasn't been created then it
	 * creates one and then opens it.
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public KingsCupDbAdapter open() throws SQLException {
		myDatabaseHelper = new DatabaseHelper(myContext);
		myDatabase = myDatabaseHelper.getWritableDatabase();
		return this;
	}

	/**
	 * Closes the database.
	 */
	public void close() {
		myDatabaseHelper.close();
	}

	/**
	 * Creates a new card entry in the cards table.
	 * 
	 * @param rdrawable
	 *            the R.drawable id from the R.java file
	 * @param cardname
	 *            The unique combination of the suit and class example 'h3'
	 *            which means a 3 of hearts
	 * @param rule
	 *            The rule that is displayed as part of the game
	 * 
	 * @return the _ID of the entry or -1 if the query fails
	 */
	public long insertCard(long rdrawable, String cardname, String rule) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_RDRAWABLE, rdrawable);
		initialValues.put(KEY_CARDNAME, cardname);
		initialValues.put(KEY_RULE, rule);

		return myDatabase.insert(TABLE_NAME, null, initialValues);
	}

	/**
	 * Delete the card with the provided identifier
	 * 
	 * @param identifier
	 *            either the rowId or the rdrawable of card to delete
	 * @param isRowId
	 *            if the identifier is the rowId then this should be true
	 *            otherwise it should be false
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteCard(long identifier, boolean isRowId) {
		if (isRowId) {
			return myDatabase.delete(TABLE_NAME, KEY_ROWID + "=" + identifier,
					null) > 0;
		} else
			return myDatabase.delete(TABLE_NAME, KEY_RDRAWABLE + "="
					+ identifier, null) > 0;

	}

	/**
	 * Return a Cursor that goes through every card in the table
	 * 
	 * @return Cursor for all cards in the table
	 */
	public Cursor fetchAllNotes() {

		return myDatabase.query(TABLE_NAME, new String[] { KEY_ROWID,
				KEY_RDRAWABLE, KEY_CARDNAME, KEY_RULE }, null, null, null,
				null, null);
	}

	/**
	 * Get a Cursor that starts at the card with the specified rowId
	 * 
	 * @param rowId
	 *            id of note to retrieve
	 * @return Cursor positioned to matching card, if found
	 * @throws SQLException
	 *             if card could not be found/retrieved
	 */
	public Cursor fetchNote(long rowId) throws SQLException {

		Cursor mCursor =

		myDatabase.query(true, TABLE_NAME, new String[] { KEY_ROWID,
				KEY_RDRAWABLE, KEY_CARDNAME, KEY_RULE }, KEY_ROWID + "="
				+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	public Cursor fetchNote(String cardname) throws SQLException {
		Cursor myCursor = myDatabase.query(true, TABLE_NAME, new String[] {
				KEY_ROWID, KEY_RDRAWABLE, KEY_CARDNAME, KEY_RULE },
				KEY_CARDNAME + "=" + cardname, null, null, null, null, null);
		if (myCursor != null) {
			myCursor.moveToFirst();
		}
		return myCursor;
	}

	/**
	 * Update the card using the details provided. The card to be updated is
	 * specified using the rowId, and it is altered to use the rdrawable,
	 * cardname, and rule values passed in
	 * 
	 * @param rowId
	 *            id of note to update
	 * @param title
	 *            value to set note title to
	 * @param body
	 *            value to set note body to
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateNote(long rowId, long rdrawable, String cardname,
			String rule) {
		ContentValues updatedValues = new ContentValues();
		updatedValues.put(KEY_RDRAWABLE, rdrawable);
		updatedValues.put(KEY_CARDNAME, cardname);
		updatedValues.put(KEY_RULE, rule);
		return myDatabase.update(TABLE_NAME, updatedValues, KEY_ROWID + "="
				+ rowId, null) > 0;
	}

	/**
	 * Update the card using the details provided. The card to be updated is
	 * specified using the cardname, and it is altered to use the cardname and
	 * rule values passed in
	 * 
	 * @param rowId
	 *            id of note to update
	 * @param title
	 *            value to set note title to
	 * @param body
	 *            value to set note body to
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateNote(String cardname, long rdrawable, String rule) {
		ContentValues updatedValues = new ContentValues();
		updatedValues.put(KEY_RDRAWABLE, rdrawable);
		updatedValues.put(KEY_RULE, rule);
		return myDatabase.update(TABLE_NAME, updatedValues, KEY_CARDNAME + "="
				+ cardname, null) > 0;
	}
}
