package com.example.suythea.hrms.PostCV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.suythea.hrms.R;

import java.util.ArrayList;

/**
 * Created by lolzzlolzz on 6/28/16.
 */
public class ListExpAdp extends ArrayAdapter<ListPostCVModel> {

    Context context;
    int resource;
    LayoutInflater layoutInflater;

    public ListExpAdp(Context _con, int _res, ArrayList<ListPostCVModel>listModels){

        super(_con,_res,listModels);

        context = _con;
        resource = _res;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListPostCVHolder listPostCVHolder;

        if (convertView == null){

            convertView = layoutInflater.inflate(this.resource,null);
            listPostCVHolder = new ListPostCVHolder();
            listPostCVHolder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitleListExp);
            listPostCVHolder.txtName = (TextView)convertView.findViewById(R.id.txtCNameListExp);
            convertView.setTag(listPostCVHolder);

        }
        else {
            listPostCVHolder = (ListPostCVHolder)convertView.getTag();
        }

        ListPostCVModel listPostCVModel = getItem(position);

        listPostCVHolder.txtTitle.setText(listPostCVModel.getTitle());
        listPostCVHolder.txtName.setText(listPostCVModel.getName());

        return convertView;
    }

}
