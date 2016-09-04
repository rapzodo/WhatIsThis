package com.danilo.whatisthis.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.danilo.whatisthis.R;
import com.danilo.whatisthis.domains.Question;
import com.danilo.whatisthis.fragments.MyCatalogFragment;
import com.danilo.whatisthis.network.NetWorkHelperSingleton;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class QuestionRecycleViewAdapter extends RecyclerView.Adapter<QuestionRecycleViewAdapter.ViewHolder> {

    private final List<Question> questions;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public QuestionRecycleViewAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
        for (int i = 0 ; i < questions.size() ; i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_thing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Question question = questions.get(position);
        holder.question = question;
        //holder.replierView.setText(question.getAnswer().getReplier().getUsername());
        holder.answerContentView.setText(question.getAnswer().getAnswerContent());
        holder.expandableLayout.setInterpolator(Utils.createInterpolator(Utils.BOUNCE_INTERPOLATOR));
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

            @Override
            public void onPreOpen() {
                createRotation(holder.expandableLayout, 180f,0f);
                expandState.put(position,true);
            }

            @Override
            public void onPreClose() {
                createRotation(holder.expandableLayout, 0f,180f);
                expandState.put(position,false);
            }
        });
        holder.expandBt.setRotation(expandState.get(position) ? 180f : 0f );
        holder.expandBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandableLayout.toggle();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView questionView;
        public final TextView answerContentView;
        //public final TextView replierView;
        //public final TextView lastUpdateView;
        public Question question;
        public ExpandableLinearLayout expandableLayout;
        public RelativeLayout expandBt;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            questionView = (TextView) view.findViewById(R.id.question);
            /*replierView = (TextView) view.findViewById(R.id.replier);
            lastUpdateView = (TextView) view.findViewById(R.id.last_update);*/
            answerContentView = (TextView) view.findViewById(R.id.answer_content);
            expandableLayout = (ExpandableLinearLayout) view.findViewById(R.id.expandableLayout);
            expandBt = (RelativeLayout) view.findViewById(R.id.arrow_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + answerContentView.getText() + "'";
        }
    }
    private void createRotation(View view, float start, float end){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation", start,end);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
    }
}
