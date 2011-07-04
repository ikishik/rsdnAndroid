package net.ikishik.RsdnAndroid;

import net.ikishik.RsdnAndroid.RsdnAndroidDBStatic.ForumGroups;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Provides access to a database of notes. Each note has a title, the note
 * itself, a creation date and a modified data.
 */
public class RsdnAndroidProvider extends ContentProvider {

    //private static final String TAG = "NotePadProvider";

    //private static final String DATABASE_NAME = "rsdn.db";
    //private static final int DATABASE_VERSION = 1;
    private static final String FORUMGROUPS_TABLE_NAME = "ForumGroups";

    private static HashMap<String, String> sForumGroupsProjectionMap;

    private static final int FORUMGROUPS = 1;
    private static final int FORUMGROUP_ID = 2;

    private static final UriMatcher sUriMatcher;

    /**
     * This class helps open, create, and upgrade the database file.
     */
    private RsdnDbHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new RsdnDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (sUriMatcher.match(uri)) {
        case FORUMGROUPS:
            qb.setTables(FORUMGROUPS_TABLE_NAME);
            qb.setProjectionMap(sForumGroupsProjectionMap);
            break;

        case FORUMGROUP_ID:
            qb.setTables(FORUMGROUPS_TABLE_NAME);
            qb.setProjectionMap(sForumGroupsProjectionMap);
            qb.appendWhere(ForumGroups._ID + "=" + uri.getPathSegments().get(1));
            break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // If no sort order is specified use the default
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = ForumGroups.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        // Get the database and run the query
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

        // Tell the cursor what uri to watch, so it knows when its source data changes
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case FORUMGROUPS:
            return ForumGroups.CONTENT_TYPE;

        case FORUMGROUP_ID:
            return ForumGroups.CONTENT_ITEM_TYPE;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        // Validate the requested uri
        if (sUriMatcher.match(uri) != FORUMGROUPS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        if (values.containsKey(ForumGroups.FORUMGROUPNAME) == false) {
            values.put(ForumGroups.FORUMGROUPNAME, "");
        }

        if (values.containsKey(ForumGroups.SORTORDER) == false) {
            values.put(ForumGroups.SORTORDER, 0);
        }

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long rowId = db.insert(FORUMGROUPS_TABLE_NAME, ForumGroups.FORUMGROUPNAME, values);
        if (rowId > 0) {
            Uri fgUri = ContentUris.withAppendedId(ForumGroups.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(fgUri, null);
            return fgUri;
        }

        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        case FORUMGROUPS:
            count = db.delete(FORUMGROUPS_TABLE_NAME, where, whereArgs);
            break;

        case FORUMGROUP_ID:
            String fgId = uri.getPathSegments().get(1);
            count = db.delete(FORUMGROUPS_TABLE_NAME, ForumGroups._ID + "=" + fgId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        case FORUMGROUPS:
            count = db.update(FORUMGROUPS_TABLE_NAME, values, where, whereArgs);
            break;

        case FORUMGROUP_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.update(FORUMGROUPS_TABLE_NAME, values, ForumGroups._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(RsdnAndroidDBStatic.AUTHORITY, "forumgroups", FORUMGROUPS);
        sUriMatcher.addURI(RsdnAndroidDBStatic.AUTHORITY, "forumgroups/#", FORUMGROUP_ID);

        sForumGroupsProjectionMap = new HashMap<String, String>();
        sForumGroupsProjectionMap.put(ForumGroups._ID, ForumGroups._ID);
        sForumGroupsProjectionMap.put(ForumGroups.FORUMGROUPNAME, ForumGroups.FORUMGROUPNAME);
        sForumGroupsProjectionMap.put(ForumGroups.SORTORDER, ForumGroups.SORTORDER);
    }
}
