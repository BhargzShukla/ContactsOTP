package page.bshukla.contactsotp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import page.bshukla.contactsotp.model.Message;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {
    class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView mMessageContactName;
        private final TextView mMessageContactOtp;
        private final TextView mMessageContactTime;

        private MessageViewHolder(View itemView) {
            super(itemView);
            mMessageContactName = itemView.findViewById(R.id.message_contact_name);
            mMessageContactOtp = itemView.findViewById(R.id.message_contact_otp);
            mMessageContactTime = itemView.findViewById(R.id.message_contact_time);
        }
    }

    private final LayoutInflater mLayoutInflater;
    private List<Message> mMessages;

    public MessageListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MessageListAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.message_list_item, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.MessageViewHolder holder, int position) {
        if (mMessages != null) {
            Message currentMessage = mMessages.get(position);
            holder.mMessageContactName.setText(currentMessage.getName());
            holder.mMessageContactOtp.setText(currentMessage.getCode());
            holder.mMessageContactTime.setText(currentMessage.getDate());
        } else {
            holder.mMessageContactName.setText("A man has no name.");
            holder.mMessageContactOtp.setText("A man's got to have a code");
            holder.mMessageContactTime.setText("It's clobberin' time!");
        }
    }

    void setMessages(List<Message> messages) {
        mMessages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMessages != null) {
            return mMessages.size();
        } else return 0;
    }
}
