package page.bshukla.contactsotp.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import page.bshukla.contactsotp.model.Message;
import page.bshukla.contactsotp.repository.MessageRepository;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository mRepository;
    private LiveData<List<Message>> mAllMessages;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MessageRepository(application);
        mAllMessages = mRepository.getAllMessages();
    }

    public LiveData<List<Message>> getAllMessages() {
        return mAllMessages;
    }

    public void insertMessage(Message message) {
        mRepository.insertMessage(message);
    }
}
