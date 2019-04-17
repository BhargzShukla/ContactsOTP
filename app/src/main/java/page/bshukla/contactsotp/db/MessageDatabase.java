package page.bshukla.contactsotp.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import page.bshukla.contactsotp.dao.MessageDao;
import page.bshukla.contactsotp.model.Message;

@Database(entities = {Message.class}, version = 2, exportSchema = false)
public abstract class MessageDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();

    private static volatile MessageDatabase INSTANCE;
    public static MessageDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MessageDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MessageDatabase.class,
                            "message_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
