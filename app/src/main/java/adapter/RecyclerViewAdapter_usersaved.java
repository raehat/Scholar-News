package adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examplefbl.R;
import com.example.examplefbl.datamodel;
import com.example.examplefbl.datamodel_userHome;
import com.example.examplefbl.datamodel_userSaved;

import java.util.ArrayList;

public class RecyclerViewAdapter_usersaved extends RecyclerView.Adapter<RecyclerViewAdapter_usersaved.myviewholder>{
    ArrayList<datamodel_userSaved> dataholder_userSaved;

    public RecyclerViewAdapter_usersaved(ArrayList<datamodel_userSaved> dataholder_userSaved) {
        this.dataholder_userSaved = dataholder_userSaved;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_userhome,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.img3.setImageResource(dataholder_userSaved.get(position).getImage());
        holder.header2.setText(dataholder_userSaved.get(position).getHeader());
        holder.desc2.setText(dataholder_userSaved.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return dataholder_userSaved.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img3;
        TextView header2,desc2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img3=itemView.findViewById(R.id.icon_img3);
            header2=itemView.findViewById(R.id.header2);
            desc2=itemView.findViewById(R.id.description2);
        }
    }
}

