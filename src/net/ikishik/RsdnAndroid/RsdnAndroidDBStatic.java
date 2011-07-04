package net.ikishik.RsdnAndroid;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Convenience definitions for NotePadProvider
 */
public final class RsdnAndroidDBStatic {
    public static final String AUTHORITY = "net.ikishik.provider.RsdnAndroid";

    // This class cannot be instantiated
    private RsdnAndroidDBStatic() {}
    
    /**
     * Notes table
     */
    public static final class ForumGroups implements BaseColumns {
        // This class cannot be instantiated
        private ForumGroups() {}

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/forumgroups");

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/net.ikishik.forumgroup";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single note.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/net.ikishik.forumgroup";

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "_id DESC";

        /**
         * The name of the forum
         * <P>Type: TEXT</P>
         */
        public static final String FORUMGROUPNAME = "forumGroupName";

        /**
         * The sortOrder of the forum
         * <P>Type: INTEGER</P>
         */
        public static final String SORTORDER = "sortOrder";
    }
}

