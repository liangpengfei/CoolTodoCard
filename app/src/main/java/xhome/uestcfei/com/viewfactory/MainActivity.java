package xhome.uestcfei.com.viewfactory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> data ;
    private List<String> deadTimes ;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        data = new ArrayList<>();
        deadTimes = new ArrayList<>();

        data.add("To learn how to play");
        data.add("Learn 50 fresh words");
        data.add("Learn the poem by heart");
        data.add("Draw a landscape");

        deadTimes.add("until Monday");
        deadTimes.add("until 21 Dec");
        deadTimes.add("until tomorrow");
        deadTimes.add("until Friday");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        MyItemAnimator animator = new MyItemAnimator(this);
        animator.setAddDuration(500);
        animator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(animator);
    }


    public void remove4(View view) {
        if (data.size() < 4) {
            return;
        }
        data.remove(data.size() - 1);
        data.remove(data.size() - 1);
        data.remove(data.size() - 1);
        data.remove(data.size() - 1);
        deadTimes.remove(deadTimes.size() - 1);
        deadTimes.remove(deadTimes.size() - 1);
        deadTimes.remove(deadTimes.size() - 1);
        deadTimes.remove(deadTimes.size() - 1);
        myAdapter.notifyItemRangeRemoved(data.size(), 4);
    }

    public void add1(View view) {
        data.add("I have a new task to do !");
        deadTimes.add("new date");
        myAdapter.notifyItemInserted(data.size());
    }

    public void remove1(View view) {
        if (data.size() == 0) {
            return;
        }
        data.remove(data.size() - 1);
        deadTimes.remove(deadTimes.size() - 1);
        myAdapter.notifyItemRemoved(data.size());
    }

    public void add4(View view) {
        data.add("To learn how to play");
        data.add("Learn 50 fresh words");
        data.add("Learn the poem by heart");
        data.add("Draw a landscape");
        deadTimes.add("until Monday");
        deadTimes.add("until 21 Dec");
        deadTimes.add("until tomorrow");
        deadTimes.add("until Friday");
        myAdapter.notifyItemRangeInserted(data.size(), 4);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

        private static final String TAG = "MyAdapter";
        private LayoutInflater inflater;

        public MyAdapter() {
            inflater = LayoutInflater.from(MainActivity.this);
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item, null);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            CardView cardView = (CardView) holder.itemView;
            switch (position % 4) {
                case 0:
                    cardView.setCardBackgroundColor(Color.parseColor("#6BC39A"));
                    holder.indicator.setImageResource(R.drawable.point1);
                    holder.deadTime.setTextColor(Color.parseColor("#76BEE6"));
                    break;
                case 1:
                    cardView.setCardBackgroundColor(Color.parseColor("#76BEE6"));
                    holder.indicator.setImageResource(R.drawable.point2);
                    holder.deadTime.setTextColor(Color.parseColor("#6BC39A"));
                    break;
                case 2:
                    cardView.setCardBackgroundColor(Color.parseColor("#E99A79"));
                    holder.indicator.setImageResource(R.drawable.point3);
                    holder.deadTime.setTextColor(Color.parseColor("#657DC1"));
                    break;
                case 3:
                    cardView.setCardBackgroundColor(Color.parseColor("#657DC1"));
                    holder.indicator.setImageResource(R.drawable.point1);
                    holder.deadTime.setTextColor(Color.parseColor("#E99A79"));
                    break;
            }
            holder.text.setText(data.get(position));
            holder.deadTime.setText(deadTimes.get(position));
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{

            private TextView text;
            private TextView deadTime;
            private ImageView indicator;

            public MyHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
                deadTime = (TextView) itemView.findViewById(R.id.deadtime);
                indicator = (ImageView) itemView.findViewById(R.id.indicator);
            }

        }
    }

}
