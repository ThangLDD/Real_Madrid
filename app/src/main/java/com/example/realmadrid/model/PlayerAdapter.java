package com.example.realmadrid.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmadrid.activity.EditPlayerActivity;
import com.example.realmadrid.R;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Player> playerList;
    private Context context;

    public PlayerAdapter(List<Player> playerList, Context context) {
        this.playerList = playerList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.textId.setText("ID: " + player.getId());
        holder.textName.setText("Name: " + player.getName());
        holder.textBirthday.setText("Birthday: " + player.getBirthday());
        holder.textKit.setText("Number Kit: " + player.getKit());
        holder.textPosition.setText("Position: " + player.getPosition());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditPlayerActivity.class);
                intent.putExtra("player", player);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textId, textName, textBirthday, textKit, textPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.item_player_id);
            textName = itemView.findViewById(R.id.item_player_name);
            textBirthday = itemView.findViewById(R.id.item_player_birthday);
            textKit = itemView.findViewById(R.id.item_player_kit);
            textPosition = itemView.findViewById(R.id.item_player_position);
        }
    }

    public void setPlayer(List<Player> playerList) {
        this.playerList = playerList;
    }
}
