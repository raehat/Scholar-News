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

import java.util.ArrayList;

public class RecyclerViewAdapter_userhome extends RecyclerView.Adapter<RecyclerViewAdapter_userhome.myviewholder>{
    ArrayList<datamodel_userHome> dataholder;

    public RecyclerViewAdapter_userhome(ArrayList<datamodel_userHome> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_userhome,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.img1.setImageResource(dataholder.get(position).getImage());
        holder.header1.setText(dataholder.get(position).getHeader());
        holder.desc1.setText(dataholder.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView header1,desc1;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.icon_img2);
            header1=itemView.findViewById(R.id.header1);
            desc1=itemView.findViewById(R.id.description1);
        }
    }
}
