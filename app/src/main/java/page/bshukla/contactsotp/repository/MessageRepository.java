package page.bshukla.contactsotp.repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import page.bshukla.contactsotp.dao.MessageDao;
import page.bshukla.contactsotp.model.Message;
import page.bshukla.contactsotp.db.MessageDatabase;
import page.bshukla.contactsotp.util.DateUtils;

import java.util.Date;
import java.util.List;

public class MessageRepository {
    private MessageDao mMessageDao;
    private LiveData<List<Message>> mAllMessages;

    public MessageRepository(Application application) {
        MessageDatabase db = MessageDatabase.getDatabase(application);
        mMessageDao = db.messageDao();
        mAllMessages = mMessageDao.getAllMessages();
    }

    public LiveData<List<Message>> getAllMessages() {
        return mAllMessages;
    }

    public void insertMessage(Message message) {
        new insertMessageAsyncTask(mMessageDao).execute(message);
    }

    private static class insertMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private MessageDao mAsyncTaskDao;

        insertMessageAsyncTask(MessageDao messageDao) {
            mAsyncTaskDao = messageDao;
        }

        @Override
        protected Void doInBackground(final Message... messages) {
            mAsyncTaskDao.insertMessage(messages[0]);
            return null;
        }
    }
}
