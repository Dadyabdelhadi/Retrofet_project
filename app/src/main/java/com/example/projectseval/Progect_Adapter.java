package com.example.projectseval;


    import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
    public class Progect_Adapter  extends RecyclerView.Adapter<Progect_Adapter.ProjectViewHolder> {
        Context context;
        List<Project> projects;

        private final OnItemClickListener listener;

        //
        public Progect_Adapter(Context context, List<Project> projects, OnItemClickListener listener) {
            this.context=context;
            this.projects = projects;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //this is where the layout will be inflated
            LayoutInflater infl=LayoutInflater.from(context);
            View view=infl.inflate(R.layout.items,parent,false);
            return new Progect_Adapter.ProjectViewHolder(view,listener);
        }

        @Override
        public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
            //Assigning values to the item view based on the position
            holder.id.setText(projects.get(position).getId());
            holder.titreP.setText(projects.get(position).getTitle());

        }

        @Override
        public int getItemCount() {
            return projects.size();
        }

        public static class ProjectViewHolder extends RecyclerView.ViewHolder{
            TextView id,titreP;

            public ProjectViewHolder(@NonNull View itemView, OnItemClickListener listener) {
                super(itemView);
                id = itemView.findViewById(R.id.editTextTextPersonName3);
                titreP = itemView.findViewById(R.id.editTextTextPersonName4);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            int p=getAdapterPosition();
                            if(p!=RecyclerView.NO_POSITION)listener.onItemClick(p);
                        }
                    }
                });
            }
        }
    }


