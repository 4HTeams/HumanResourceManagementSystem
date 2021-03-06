package com.example.suythea.hrms.SearchJob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suythea.hrms.Home.ListJobHolder;
import com.example.suythea.hrms.Home.ListJobModel;
import com.example.suythea.hrms.Home.MainHome;
import com.example.suythea.hrms.Interfaces.List_CV_And_Job_Interface;
import com.example.suythea.hrms.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lolzzlolzz on 6/28/16.
 */
public class ListSearchAdp extends ArrayAdapter<ListJobModel> {

    Context context;
    int resource;
    LayoutInflater layoutInflater;

    public ListSearchAdp(Context _con, int _res, ArrayList<ListJobModel>listModels){

        super(_con,_res,listModels);

        context = _con;
        resource = _res;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListJobHolder listJobHolder;

        if (convertView == null){

            convertView = layoutInflater.inflate(this.resource,null);
            listJobHolder = new ListJobHolder();
            listJobHolder.imgProfile = (ImageView) convertView.findViewById(R.id.imgProfileMainJob);
            listJobHolder.txtCName = (TextView)convertView.findViewById(R.id.txtCNameMainJob);
            listJobHolder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitleMainJob);
            listJobHolder.txtExp = (TextView)convertView.findViewById(R.id.txtExpMainJob);
            listJobHolder.txtSalary = (TextView)convertView.findViewById(R.id.txtSalaryMainJob);
            listJobHolder.txtDeadline = (TextView)convertView.findViewById(R.id.txtDeadlineMainJob);
            convertView.setTag(listJobHolder);

        }
        else {
            listJobHolder = (ListJobHolder)convertView.getTag();
        }

        ListJobModel listJobModel = getItem(position);

        listJobHolder.txtCName.setText(listJobModel.getcName());
        listJobHolder.txtTitle.setText(listJobModel.getTitle());
        listJobHolder.txtExp.setText("Experience : " + listJobModel.getYearEx());
        listJobHolder.txtSalary.setText("Salary : " + listJobModel.getSalary());
        listJobHolder.txtDeadline.setText("Deadline : " + listJobModel.getDeadline());

        Picasso.with(context)
                .load("http://bongnu.myreading.xyz/profile_images/" + listJobModel.getImgUrl() + ".jpg")
                .placeholder(context.getResources().getIdentifier("no_profile","mipmap",context.getPackageName()))
                .into(listJobHolder.imgProfile, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });

        return convertView;
    }

}
